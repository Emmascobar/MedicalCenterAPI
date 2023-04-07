package com.medicalcenterapi.model.scores;

import java.time.LocalDate;

public class PregnancyInformation {

    private LocalDate lastPeriod;
    private Integer lengthOfCycles;
    private LocalDate conceptionDay;
    public PregnancyInformation() {
    }

    public PregnancyInformation(LocalDate lastPeriod, Integer lengthOfCycles, LocalDate conceptionDay) {
        this.lastPeriod = lastPeriod;
        this.lengthOfCycles = lengthOfCycles;
        this.conceptionDay = conceptionDay;
    }

    public LocalDate getLastPeriod() {
        return lastPeriod;
    }

    public void setLastPeriod(LocalDate lastPeriod) {
        this.lastPeriod = lastPeriod;
    }

    public Integer getLengthOfCycles() {
        return lengthOfCycles;
    }

    public void setLengthOfCycles(Integer lengthOfCycles) {
        this.lengthOfCycles = lengthOfCycles;
    }

    public LocalDate getConceptionDay() {
        return conceptionDay;
    }

    public void setConceptionDay(LocalDate conceptionDay) {
        this.conceptionDay = conceptionDay;
    }
}
