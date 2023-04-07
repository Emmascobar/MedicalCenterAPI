package com.medicalcenterapi.model.scores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//There is a POJO CLASS to STORE a big businnes logical to aplicate in Doctor Service.
public class CriteriaForDVT {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private Integer finalScore;
  private String finalClassification;
  private String management;

  /* The Wells’ DVT Criteria can be used in the outpatient and emergency department setting.
  By risk stratifying to low risk (Wells’ Score <2) and a negative D-dimer, the clinician can
  exclude the need for ultrasound (US) to rule out DVT. */
  public void wellsDVTClassification() {

    log.info("Next calculate give the results of DVT Wells Criteria. This meant to aid clinical decision making and not force management. " +
            "Should only be applied after a detailed history and physical is performed. You only have to answer whit (Y)ES or (N)OT");

    Scanner scanner = new Scanner(System.in);
    Integer score = 0;

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
    finalScore = score;
    if(finalScore == 0){
      finalClassification = "LOWER RISK";
      management = "•\tA score of 0 or LOWER is associated with DVT unlikely with a prevalence of DVT of 5%.\n" +
              "o\tThese patients should proceed to d-dimer testing:\n" +
              "\uF0A7\tA negative high or moderate sensitivity d-dimer results in a probability <1 % and no further imaging is required.\n" +
              "\uF0A7\tA positive d-dimer should proceed to US testing.\n" +
              "\uF0A7\tA negative US is sufficient for DVT rule out.\n" +
              "\uF0A7\tA positive US is concerning for DVT; strongly consider treatment with anticoagulation.\n";

    } else if (finalScore >0 && finalScore <3) {
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
  }
}