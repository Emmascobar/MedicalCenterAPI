package com.medicalcenterapi.services.impl;

import com.medicalcenterapi.controller.dto.BloodPressureRegistryDTO;
import com.medicalcenterapi.model.scores.*;
import com.medicalcenterapi.model.users.Patient;
import com.medicalcenterapi.model.users.Role;
import com.medicalcenterapi.model.utils.ComplementaryTest;
import com.medicalcenterapi.model.utils.MedicalReport;
import com.medicalcenterapi.model.utils.PharmacyOrder;
import com.medicalcenterapi.repository.scores.BloodPressureRegistryRepository;
import com.medicalcenterapi.repository.users.DoctorRepository;
import com.medicalcenterapi.repository.users.MedicalReportRepository;
import com.medicalcenterapi.repository.users.PatientRepository;
import com.medicalcenterapi.repository.users.UserRepository;
import com.medicalcenterapi.repository.utils.ComplementaryTestRepository;
import com.medicalcenterapi.repository.utils.PharmacyOrderRepository;
import com.medicalcenterapi.services.interfaces.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BloodPressureRegistryRepository bloodPressureRegistryRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    String encodedPassword;
    @Autowired
    private PharmacyOrderRepository pharmacyOrderRepository;
    @Autowired
    private MedicalReportRepository medicalReportRepository;
    @Autowired
    private ComplementaryTestRepository complementaryTestRepository;
    @Autowired
    private CriteriaForDVT criteriaForDVT;
    @Autowired
    GlasgowComaScale glasgowComaScale;

    /**
     * METHODS
     **/
    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findPatientById(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        return optionalPatient;
    }

    public Optional<Patient> findPatientByNameOrSurname(String name, String surname) {
        Optional<Patient> optionalPatient = patientRepository.findByNameOrSurname(name, surname);
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        return optionalPatient;
    }

    public Optional<Patient> findPatientByNin(String nin) {
        Optional<Patient> optionalPatient = patientRepository.findByNin(nin);
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        return optionalPatient;
    }

    public Patient addNewPatient(Patient patient) {
        encodedPassword = passwordEncoder.encode(patient.getPassword());
        patient.setPassword(encodedPassword);
        patient.setRoles(List.of(new Role("PATIENT")));
        patientRepository.save(patient);
        patientRepository.save(patient);
        return patient;
    }

    public PharmacyOrder addNewPharmacyOrder(Long patientId, PharmacyOrder pharmacyOrder) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        } else {
            pharmacyOrderRepository.save(pharmacyOrder);
            optionalPatient.get().setPharmacyOrder(pharmacyOrder);
            patientRepository.save(optionalPatient.get());
        }
        return pharmacyOrder;
    }

    public MedicalReport addNewMedicalReport(Long patientId, MedicalReport medicalReport) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        } else {
            medicalReportRepository.save(medicalReport);
            optionalPatient.get().setMedicalReports((List<MedicalReport>) medicalReport);
            patientRepository.save(optionalPatient.get());
        }
        return medicalReport;
    }

    public ComplementaryTest addNewComplementaryTest(Long patientId, ComplementaryTest complementaryTest) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        } else {
            complementaryTestRepository.save(complementaryTest);
            optionalPatient.get().setComplementaryTests((List<ComplementaryTest>) complementaryTest);
            patientRepository.save(optionalPatient.get());
        }
        return complementaryTest;
    }


    /**--- SCORE LOGICS ---**/

    /**
     * --- CALCULATE AVERAGES ---
     **/
    // To get the Classification you need have PAD & PAS average.
    // Controlled the pressure during 7 days, get the results and set the average.
    public OptionalDouble averageCalculator(double... numbers) {
        OptionalDouble average = Arrays.stream(numbers).average();
        return average;
    }


    /**
     * --- BLOOD PRESSURE CLASSIFICATION ---
     **/
    // Now will can classified the symptom.
    public BloodPressureRegistry BPClassification(Long patientId, BloodPressureRegistryDTO bloodPressureRegistryDTO) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        BloodPressureRegistry bloodPressureRegistry = new BloodPressureRegistry();
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        } else {
            bloodPressureRegistry.setAveragePAS(bloodPressureRegistryDTO.getAveragePAS());
            bloodPressureRegistry.setAveragePAD(bloodPressureRegistryDTO.getAveragePAD());
            bloodPressureRegistry.setComorbidity(bloodPressureRegistryDTO.getComorbidity());
            bloodPressureRegistry.setPatient(optionalPatient.get());

            //Risk Values Classification:
            String normal = "NORMAL BLOOD PRESSURE";
            String risk = "HIGH RISK OF HIGH BLOOD PRESSURE";
            String HBP1 = "HIGH BLOOD PRESSURE GRADE I";
            String HBP2 = "HIGH BLOOD PRESSURE GRADE II";

            if ((bloodPressureRegistryDTO.getAveragePAD() <= 80 && bloodPressureRegistryDTO.getAveragePAS() <= 120)) {
                bloodPressureRegistry.setClassification(normal);
            } else if ((bloodPressureRegistryDTO.getAveragePAD() > 80 && bloodPressureRegistryDTO.getAveragePAD() < 89 && bloodPressureRegistryDTO.getAveragePAS() > 120 && bloodPressureRegistryDTO.getAveragePAS() < 139)) {
                bloodPressureRegistry.setClassification(risk);
            } else if ((bloodPressureRegistryDTO.getAveragePAD() >= 90 && bloodPressureRegistryDTO.getAveragePAD() < 99 && bloodPressureRegistryDTO.getAveragePAS() >= 140 && bloodPressureRegistryDTO.getAveragePAS() < 159)) {
                bloodPressureRegistry.setClassification(HBP1);
            } else if ((bloodPressureRegistryDTO.getAveragePAD() >= 100 && bloodPressureRegistryDTO.getAveragePAS() >= 160)) {
                bloodPressureRegistry.setClassification(HBP2);
            }

//          Treatment suggested from the results and Comorbidity:
            if (bloodPressureRegistry.getClassification().equals(HBP1) || bloodPressureRegistry.getClassification().equals(HBP2) && bloodPressureRegistry.getComorbidity().toString().equals("CARDIAC_DISEASE")) {
                bloodPressureRegistry.setSuggestedTreatment("Can considerate: Diuretics, BB, IECAs, ARAsII and SPIROLACTONE");
            } else if (bloodPressureRegistry.getClassification().equals(HBP1) || bloodPressureRegistry.getClassification().equals(HBP2) && bloodPressureRegistry.getComorbidity().toString().equals("IAM_DISEASE")) {
                bloodPressureRegistry.setSuggestedTreatment("Can considerate: BB, IECAs and SPIROLACTONE");
            } else if (bloodPressureRegistry.getClassification().equals(HBP1) || bloodPressureRegistry.getClassification().equals(HBP2) && bloodPressureRegistry.getComorbidity().toString().equals("CORONARY_DISEASE")) {
                bloodPressureRegistry.setSuggestedTreatment("Can considerate: Diuretics, BB and IECAs");
            } else if (bloodPressureRegistry.getClassification().equals(HBP1) || bloodPressureRegistry.getClassification().equals(HBP2) && bloodPressureRegistry.getComorbidity().toString().equals("DIABETES")) {
                bloodPressureRegistry.setSuggestedTreatment("Can considerate: Diuretics, BB, IECAs, Calcio-Antagonist and ARAsII");
            } else if (bloodPressureRegistry.getClassification().equals(HBP1) || bloodPressureRegistry.getClassification().equals(HBP2) && bloodPressureRegistry.getComorbidity().toString().equals("CHRONIC_RENAL_FAILURE")) {
                bloodPressureRegistry.setSuggestedTreatment("Can considerate: IECAs or ARAsII");
            } else if (bloodPressureRegistry.getClassification().equals(risk) && bloodPressureRegistry.getComorbidity().toString().equals("NONE")) {
                bloodPressureRegistry.setSuggestedTreatment("Considerate a prevent treatment with: IECAs or Diuretics");
            } else {
                bloodPressureRegistry.setSuggestedTreatment("Pressure it's OK. Not need treatment now.");
            }
//          Save on BD.
            bloodPressureRegistryRepository.save(bloodPressureRegistry);
        }
        return bloodPressureRegistry;
    }


    /**
     * --- DVT CLASSIFICATION: WELLS CRITERIA---
     **/
    /* The Wells’ DVT Criteria can be used in the outpatient and emergency department setting.
  By risk stratifying to low risk (Wells’ Score <2) and a negative D-dimer, the clinician can
  exclude the need for ultrasound (US) to rule out DVT. */
    public MedicalReport wellsDVTClassification(Long patientId) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        MedicalReport newMedicalReport;
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        } else {
            log.info("Next calculate give the results of DVT Wells Criteria. This meant to aid clinical decision making and not force management. " +
                    "Should only be applied after a detailed history and physical is performed. You only have to answer whit (Y)ES or (N)OT");

            Scanner scanner = new Scanner(System.in);

            Integer score = 0;
            String finalClassification = "";
            String management = "";

            ArrayList<String> questions = new ArrayList<String>(Arrays.asList("¿Active Cancer", "Bedridden recently >3 days or major surgery within 12 weeks",
                    "Calf swelling >3 cm compared to the other leg", "Collateral (nonvaricose) superficial veins present", "Entire leg swollen",
                    "Localized tenderness along the deep venous system", "Pitting edema, confined to symptomatic leg",
                    "Paralysis, paresis, or recent plaster immobilization of the lower extremity", "Previously documented DVT",
                    "Alternative diagnosis to DVT as likely or more likely"));

            for (int i = 0; i < questions.size(); i++) {
                System.out.println(questions.get(i));
                String command = scanner.nextLine();
                if (command == "Y") {
                    score++;
                }
            }
            if (score == 0) {
                finalClassification = "LOWER RISK";
                management = "•\tA score of 0 or LOWER is associated with DVT unlikely with a prevalence of DVT of 5%.\n" +
                        "o\tThese patients should proceed to d-dimer testing:\n" +
                        "\uF0A7\tA negative high or moderate sensitivity d-dimer results in a probability <1 % and no further imaging is required.\n" +
                        "\uF0A7\tA positive d-dimer should proceed to US testing.\n" +
                        "\uF0A7\tA negative US is sufficient for DVT rule out.\n" +
                        "\uF0A7\tA positive US is concerning for DVT; strongly consider treatment with anticoagulation.\n";

            } else if (score > 0 && score < 3) {
                finalClassification = "MODERATE RISK";
                management = "•\tA score of 1-2 is considered MODERATE risk with a pretest probability of 17%.*\n" +
                        "o\tThese patients should proceed to high-sensitivity d-dimer testing (moderate sensitivity d-dimer is not sufficient).\n" +
                        "\uF0A7\tA negative high-sensitivity d-dimer is sufficient for rule out of DVT in a moderate risk patient with a probability of <1%.\n" +
                        "\uF0A7\tA positive high sensitivity d-dimer should proceed to US testing.\n" +
                        "\uF0A7\tA negative US is sufficient for ruling out DVT.\n" +
                        "\uF0A7\tA positive US is concerning for DVT, strongly consider treatment with anticoagulation.\n";

            } else {
                finalClassification = "HIGHER RISK";
                management = "•\tA score of 3 or HIGHER suggests DVT is likely. Pretest probability 17-53%.\n" +
                        "o\tAll DVT likely patients should receive a diagnostic US.\n" +
                        "o\tD-dimer testing should be utilized to help risk-stratify these DVT-likely patients.\n" +
                        "\uF0A7\tIn DVT likely patients with negative d-dimer:\n" +
                        "\uF0A7\tA negative US is sufficient for ruling out DVT, consider discharge.\n" +
                        "\uF0A7\tA positive US should be concerning for DVT, strongly consider treatment with anticoagulation.\n" +
                        "\uF0A7\tIn DVT likely patients with a positive d-dimer:\n" +
                        "\uF0A7\tA positive US should be concerning for DVT, strongly consider treatment with anticoagulation.\n" +
                        "\uF0A7\tA negative US is still concerning for DVT. A repeat US should be performed within 1 week for re-evaluation.\n";
            }

            newMedicalReport = new MedicalReport();
            newMedicalReport.setPatient(optionalPatient.get());
            newMedicalReport.setReport(
                    "WELLS CRITERIAS FOR DVT - RESULTS:\n" +
                            "Total Score: " + score + "\n" +
                            "Classification: " + finalClassification + "\n" +
                            "Management: " + management
            );
        }
        return medicalReportRepository.save(newMedicalReport);
    }


    /**
     * --- BODY MASS AND FAT INDEX CALCULATOR ---
     **/
    public MedicalReport BMIClassification(Long patientID, Measures measures) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientID);
        MedicalReport newMedicalReport = new MedicalReport();
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        } else {
            //formula BMI (Body Mass Index)
            double formulaBMI = (measures.getWeight() / Math.pow(2, measures.getHeight()));
            if (formulaBMI < 18.5) {
                measures.setBMI(formulaBMI + "Underweight");
            } else if (formulaBMI > 18.4 && formulaBMI < 25) {
                measures.setBMI(formulaBMI + "Normal weight");
            } else if (formulaBMI >= 25 && formulaBMI < 30) {
                measures.setBMI(formulaBMI + "Overweight. Please review the results of BODY FAT MASS and take actions");
            } else {
                measures.setBMI(formulaBMI + "Obesity. Please review the results of BODY FAT MASS and take actions");
            }

            // Formula TBF (Total Body Fat)
            if (Objects.equals(measures.getGender(), "Male")) { //If Male
                measures.setTBF((1.20 * formulaBMI + 0.23 * measures.getAge() - 16.2));
            } else { //If Female
                measures.setTBF((1.20 * formulaBMI + 0.23 * measures.getAge() - 5.4));
            }

            newMedicalReport.setReport(
                    "BODY MASS INDEX: \n" +
                            "Weight: " + measures.getWeight() + "\n" +
                            "Height: " + measures.getHeight() + "\n" +
                            "BMI: " + measures.getBMI() + "\n" +
                            "TBF" + measures.getTBF()
            );
            newMedicalReport.setPatient(optionalPatient.get());
            patientRepository.save(optionalPatient.get());
            medicalReportRepository.save(newMedicalReport);
        }
        return newMedicalReport;
    }

    /**
     * --- LEAN BODY MASS CALCULATOR ---
     **/
    /*Lean body mass (LBM) is a part of body composition that is defined as the difference between total body weight and body fat weight.
     This means that it counts the mass of all organs except body fat, including bones, muscles, blood, skin, and everything else.
     While the percentage of LBM is usually not computed, it on average ranges between 60-90% of total body weight. Generally, men have a
     higher proportion of LBM than women do. The dosages of some anesthetic agents, particularly water-soluble drugs, are routinely based on the LBM.
     Some medical exams also use the LBM values. For body fitness and routine daily life, people normally care more about body fat percentage than LBM. */
    public MedicalReport LBMClassification(Long patientID, Measures measures) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientID);
        MedicalReport newMedicalReport = new MedicalReport();
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        } else {
            double bodyWeight = measures.getWeight();
            double height = measures.getHeight();
            // Formulas: are different by man or woman.
            if (Objects.equals(measures.getGender(), "Male")) { // If Male
                measures.setLBM((1.1 * bodyWeight - 128 * Math.pow(2, bodyWeight / height)));
            } else { // If Female
                measures.setLBM((1.07 * bodyWeight - 148 * Math.pow(2, bodyWeight / height)));
            }

            newMedicalReport.setReport(
                    "LEAN BODY MASS INDEX: \n" +
                            "Weight: " + bodyWeight + "\n" +
                            "Height: " + height + "\n" +
                            "LBM: " + measures.getLBM()
            );
            newMedicalReport.setPatient(optionalPatient.get());
            patientRepository.save(optionalPatient.get());
            medicalReportRepository.save(newMedicalReport);
        }
        return newMedicalReport;
    }

    /**
     * --- GLASGOW SCALE CALCULATOR ---
     **/
    public MedicalReport glasgowComaScale(Long patientID, GlasgowComaScale glasgowComaScale) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientID);
        MedicalReport newMedicalReport = new MedicalReport();
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        } else {
            log.info("Based on motor responsiveness, verbal performance, and eye opening to appropriate stimuli, the " +
                    "Glascow Coma Scale was designed and should be used to assess the depth and duration coma and impaired consciousness. " +
                    "This scale helps to gauge the impact of a wide variety of conditions such as acute brain damage due to traumatic and/or " +
                    "vascular injuries or infections, metabolic disorders (e.g., hepatic or renal failure, hypoglycemia, diabetic ketosis), etc. " +
                    "Education is necessary to the proper application of this scale.");
            log.warn("ATTENTION. YOU NEED DEFINE THAT FASTEST. CHOICE ONE OPTION OF EVERY COLUM AND THE RESULTS AUTOMATICALLY APPEARS");

            Integer totalScore = 0;
            String description = "";

            //PUT IN TRUE AND ADD PARAMETERS TO THE TOTAL SCORE
            if (glasgowComaScale.isMRObeys()) {
                totalScore = +6;
            } else if (glasgowComaScale.isVROriented()
                    && glasgowComaScale.isMRPurposefulPain()) {
                totalScore = +5;
            } else if (glasgowComaScale.isEOREyesSpontaneous()
                    && glasgowComaScale.isVRConfused()
                    && glasgowComaScale.isMRWithdrawsPain()) {
                totalScore = +4;
            } else if (glasgowComaScale.isEOREyesVerbal()
                    && glasgowComaScale.isVRInappropiate()
                    && glasgowComaScale.isMRFlexionPain()) {
                totalScore = +3;
            } else if (glasgowComaScale.isEOREyesPain()
                    && glasgowComaScale.isVRIncomprenhensible()
                    && glasgowComaScale.isMRExtensionPain()) {
                totalScore = +2;
            } else if (glasgowComaScale.isEORNoResponse()
                    && glasgowComaScale.isVRNoResponse()
                    && glasgowComaScale.isMRNoResponse()) {
                totalScore = +1;
            }

            if (totalScore <= 8) {
                description = "SEVERE SCORE. COMA. TAKE ACTION INTERMEDIATELY";
            } else if (totalScore >= 9 && totalScore <= 12) {
                description = "MODERATE SCORE. POSSIBLE COMA. TAKE ACTION INTERMEDIATELY";
            } else {
                description = "MINOR SCORE. Found causes and take the necessary actions";
            }
            newMedicalReport.setPatient(optionalPatient.get());
            newMedicalReport.setReport(
                    "GLASGOW COMA SCALES:" + "\n" +
                            "TOTAL SCORE: " + totalScore.toString() + ". " + description + "."
            );
            patientRepository.save(optionalPatient.get());
            medicalReportRepository.save(newMedicalReport);
        }
        return newMedicalReport;
    }

    /**
     * --- PEDIATRIC DRUGS DOSAGE CALCULATORS ---
     **/
    public MedicalReport DosageCalculator(Long patientID, String drugName, double weight, double dosageOrdered, double mgOnHand, double mlOnHand) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientID);
        MedicalReport newMedicalReport = new MedicalReport();
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        } else {
            log.info(" REMEMBER:" +
                    "DOSAGE ORDERED (DO) is the total doses ordered by the doctor per KILOGRAMS. Ex 4mg/kg." +
                    "The REQUIRED DOSES is the resul of the multiplication of weight in kgs by the DO" +
                    "then you need to simplificate depends of the presentation of medication = mg/ml");
            // Formula:
            double requiredDosage = weight * dosageOrdered;
            double volumenDosage = (requiredDosage / mgOnHand) * mlOnHand;

            newMedicalReport.setPatient(optionalPatient.get());
            newMedicalReport.setReport(
                    "DOSAGE TO ADMINISTRATE:" + "\n" +
                            "DRUG: " + drugName + "\n" +
                            "DOSAGE ORDERED BY DOCTOR: " + dosageOrdered + "\n" +
                            "REQUIRED DOSAGE: " + requiredDosage + "\n" +
                            "BASES IN YOUR MEDICATION ON HAND NEED TO ADMINISTRATE: " + volumenDosage
            );
            patientRepository.save(optionalPatient.get());
            medicalReportRepository.save(newMedicalReport);
        }
        return newMedicalReport;
    }

    public MedicalReport DosagePerDoses(Long patientID, String drugName, double weight, double dosageOrdered, Integer timesPerDay, double minRange, double maxRange) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientID);
        MedicalReport newMedicalReport = new MedicalReport();
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        } else {
            log.info("Firstly is necessary know the MIN and MAX Dosage according to the weight of patient in KGS.");
            // Formula:
            double maxDosage = weight * maxRange;
            double minDosage = weight * minRange;
            double requiredDosage = dosageOrdered * timesPerDay;

            int hours = 0;
            if (timesPerDay == 1) {
                hours = 24;
            } else if (timesPerDay == 2) {
                hours = 12;
            } else if (timesPerDay == 3) {
                hours = 8;
            } else if (timesPerDay == 4) {
                hours = 6;
            }

            if (requiredDosage > minDosage && requiredDosage < maxDosage) {
                boolean isSecure = true;
                log.info("The Dosage ordered are within the desired range");
                newMedicalReport.setPatient(optionalPatient.get());
                newMedicalReport.setReport(
                        "DOSAGE TO ADMINISTRATE:" + "\n" +
                                "DRUG: " + drugName + "\n" +
                                "DOSAGE ORDERED PER DOSES: " + dosageOrdered + "\n" +
                                "TOTAL REQUIRED DOSAGE PER DAY: " + requiredDosage + "\n" +
                                "SUMMARY: Need administrate" + dosageOrdered + "every " + hours + "hs."
                );
            }
            patientRepository.save(optionalPatient.get());
            medicalReportRepository.save(newMedicalReport);
        }
        return newMedicalReport;
    }

    /**
     * --- PREGNANCY INFORMATION ---
     **/
    public MedicalReport pregnancyInformation(Long patientID, PregnancyInformation pregnancyInformation) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientID);
        MedicalReport newMedicalReport = new MedicalReport();
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        } else {
            pregnancyInformation.setConceptionDay(pregnancyInformation.getLastPeriod().minusDays(7).minusMonths(3));
            newMedicalReport.setReport(
                    "PREGNANCY INFORMATION:" + "\n" +
                            "CONCEPTION DAY: " + pregnancyInformation.getConceptionDay() + " +/- 7 DAYS" + "\n" +
                            "LAST PERIOD: " + pregnancyInformation.getLastPeriod() + "\n" +
                            "LENGTH OF CICLES: " + pregnancyInformation.getLengthOfCycles()
            );
        }
        patientRepository.save(optionalPatient.get());
        medicalReportRepository.save(newMedicalReport);
        return newMedicalReport;
    }

    /**
     * --- CHA2DS2VASScore FOR STROKE RISK IN PATIENT WITH HISTORY OF AURICULAR FIBRILLATION---
     **/
    public MedicalReport CHA2DS2VASScore(Long patientID, CHA2DS2VASScore cha2DS2VASScore) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientID);
        MedicalReport newMedicalReport = new MedicalReport();
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        } else {
            //CALCULATOR ACCORDING DATA
            Integer totalScore = 0;
            int edad = (LocalDate.now().getYear() - optionalPatient.get().getDayOfBirth().getYear());
            if (edad >= 65 && edad < 75) {
                totalScore++;
            } else if (edad >= 75) {
                totalScore = +2;
            } else if (cha2DS2VASScore.isStrokeVascularHistory()) {
                totalScore = +2;
            } else if (cha2DS2VASScore.isHeartFailure()) {
                totalScore++;
            } else if (cha2DS2VASScore.isHighArterialPressure()) {
                totalScore++;
            } else if (cha2DS2VASScore.isDiabetes()) {
                totalScore++;
            } else if (cha2DS2VASScore.isDiseaseVascularHistory()) {
                totalScore++;
            } else if (Objects.equals(optionalPatient.get().getGenre(), "Female")) {
                totalScore++;
            }

            String riskClassification = "";
            if (totalScore == 0 && Objects.equals(optionalPatient.get().getGenre(), "Female") || totalScore == 1 && Objects.equals(optionalPatient.get().getGenre(), "Male")) {
                riskClassification = "Low risk. Does not require anticoagulation.";
            }
            if (totalScore == 1 && Objects.equals(optionalPatient.get().getGenre(), "Female") || totalScore == 2 && Objects.equals(optionalPatient.get().getGenre(), "Male")) {
                riskClassification = "Moderate risk. Anticoagulation may be considered.";
            }
            if (totalScore >= 2 && Objects.equals(optionalPatient.get().getGenre(), "Female") || totalScore >= 3 && Objects.equals(optionalPatient.get().getGenre(), "Male")) {
                riskClassification = "Moderate-high risk. Anticoagulation is recommended unless contraindicated.";
            }
            newMedicalReport.setReport(
                    "STROKE RISK IN PATIENT WITH HISTORY OF AURICULAR FIBRILLATION:" + "\n" +
                            "RISK AND BEHAVIOR: " + riskClassification
            );
        }
        patientRepository.save(optionalPatient.get());
        medicalReportRepository.save(newMedicalReport);
        return newMedicalReport;
    }

    /**
     * --- GLOMERULAR FILTRATION RATE ---
     **/
    public MedicalReport RenalFunctionClassification(Long patientID, RenalFunction renalFunction) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientID);
        MedicalReport newMedicalReport = new MedicalReport();
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        } else {
            int edad = (LocalDate.now().getYear() - optionalPatient.get().getDayOfBirth().getYear());
            double GFR = 0;
            // Formulas:
            if (Objects.equals(optionalPatient.get().getGenre(), "Female") && renalFunction.getSerumCreatinine() <= 62) {
                GFR = (144 * Math.pow(-0.329, renalFunction.getSerumCreatinine() / 62) * Math.pow(edad, 0.993));
            } else if (Objects.equals(optionalPatient.get().getGenre(), "Female") && renalFunction.getSerumCreatinine() > 62) {
                GFR = (144 * Math.pow(-0.329, renalFunction.getSerumCreatinine() / 62) * Math.pow(edad, 0.993));
            } else if (Objects.equals(optionalPatient.get().getGenre(), "Male") && renalFunction.getSerumCreatinine() <= 80) {
                GFR = (141 * Math.pow(-0.329, renalFunction.getSerumCreatinine() / 80) * Math.pow(edad, 0.993));
            } else if (Objects.equals(optionalPatient.get().getGenre(), "Male") && renalFunction.getSerumCreatinine() > 80) {
                GFR = (141 * Math.pow(-0.329, renalFunction.getSerumCreatinine() / 80) * Math.pow(edad, 0.993));
            }
            if (renalFunction.isBlackRace()) {
                GFR = (GFR * 1.1559);
            }
            renalFunction.setGFR(GFR);
            if (GFR > 90) {
                renalFunction.setClassification("Healthy kidneys or kidney damage with normal or high GFR");
            } else if (GFR >= 60 && GFR < 90) {
                renalFunction.setClassification("Kidney damage and mild decrease in GFR");
            } else if (GFR >= 30 && GFR < 60) {
                renalFunction.setClassification("Moderate decrease in GFR");
            } else if (GFR >= 15 && GFR < 30) {
                renalFunction.setClassification("Severe decrease in GFR");
            } else {
                renalFunction.setClassification("Kidney Failure");
            }
        }
        newMedicalReport.setReport(
                "RENAL FUNCTION CLASSIFICATION ACCORDING TO CKD-EPI FORMULA:" + "\n" +
                        "SERUM CREATININE: " + renalFunction.getSerumCreatinine() + "\n" +
                        "GLOMERULAR FILTRATION RATE: " + renalFunction.getGFR() + "\n" +
                        "RISK CLASSIFICATION: " + renalFunction.getClassification()
        );
        patientRepository.save(optionalPatient.get());
        medicalReportRepository.save(newMedicalReport);
        return newMedicalReport;
    }
}