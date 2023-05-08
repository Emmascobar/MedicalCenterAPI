package com.medicalcenterapi.controller.dto;

import com.medicalcenterapi.model.others.Comorbidity;

public class BloodPressureRegistryDTO {

    private double averagePAS;
    private double averagePAD;
    private Comorbidity comorbidity;

    public BloodPressureRegistryDTO() {
    }

    public double getAveragePAS() {
        return averagePAS;
    }

    public void setAveragePAS(double averagePAS) {
        this.averagePAS = averagePAS;
    }

    public double getAveragePAD() {
        return averagePAD;
    }

    public void setAveragePAD(double averagePAD) {
        this.averagePAD = averagePAD;
    }

    public Comorbidity getComorbidity() {
        return comorbidity;
    }

    public void setComorbidity(Comorbidity comorbidity) {
        this.comorbidity = comorbidity;
    }
}
