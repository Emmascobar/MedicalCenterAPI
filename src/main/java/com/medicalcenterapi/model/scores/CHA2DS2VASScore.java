package com.medicalcenterapi.model.scores;

public class CHA2DS2VASScore {

    /*
     The CHADS2-VASc Score is the evolution of a widely used instrument in order to estimate, in a relatively simple way, the risk of suffering a Cerebrovascular Accident in the case of patients with Atrial Fibrillation of non-rheumatic origin, the CHADS2. Fundamentally, both are used to decide which of these patients should receive anticoagulant or antiplatelet treatment. Its result is a score, which indicates the percentage of risk of suffering a stroke by the patient in the following twelve months. To the acronyms of CHADS2, which come from: Congestive Heart Failure, Hypertension, Age, Diabetes, Previous Stroke, the evaluation of previous cardiovascular disease, and sex are added, which are the parameters on which the evaluation system is based. CHADS2-VASc responds to the criticism that was made of its predecessor, that it did not include some factors that could also influence the appearance of stroke, although it is slightly more complex. Its use is recommended by the European and Canadian Societies of Cardiology. Both the use of the CHADS2 and the CHADS2-VASc are recommended in conjunction with other bleeding risk assessment systems (HAS-BLED, HEMORR2HAGES, ATRIA, and others).
     */

    private boolean heartFailure;
    private boolean highArterialPressure;
    private boolean diabetes;
    private boolean StrokeVascularHistory;
    private boolean DiseaseVascularHistory;
    private boolean isWomen;

    public CHA2DS2VASScore() {
    }

    public CHA2DS2VASScore(boolean heartFailure, boolean highArterialPressure, boolean diabetes, boolean strokeVascularHistory, boolean diseaseVascularHistory, boolean isWomen) {
        this.heartFailure = heartFailure;
        this.highArterialPressure = highArterialPressure;
        this.diabetes = diabetes;
        StrokeVascularHistory = strokeVascularHistory;
        DiseaseVascularHistory = diseaseVascularHistory;
        this.isWomen = isWomen;
    }

    public boolean isHeartFailure() {
        return heartFailure;
    }

    public void setHeartFailure(boolean heartFailure) {
        this.heartFailure = heartFailure;
    }

    public boolean isHighArterialPressure() {
        return highArterialPressure;
    }

    public void setHighArterialPressure(boolean highArterialPressure) {
        this.highArterialPressure = highArterialPressure;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public boolean isStrokeVascularHistory() {
        return StrokeVascularHistory;
    }

    public void setStrokeVascularHistory(boolean strokeVascularHistory) {
        StrokeVascularHistory = strokeVascularHistory;
    }

    public boolean isDiseaseVascularHistory() {
        return DiseaseVascularHistory;
    }

    public void setDiseaseVascularHistory(boolean diseaseVascularHistory) {
        DiseaseVascularHistory = diseaseVascularHistory;
    }

    public boolean isWomen() {
        return isWomen;
    }

    public void setWomen(boolean women) {
        isWomen = women;
    }
}
