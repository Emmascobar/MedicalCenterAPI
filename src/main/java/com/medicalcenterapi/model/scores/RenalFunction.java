package com.medicalcenterapi.model.scores;

public class RenalFunction {
    private double serumCreatinine;
    private double GFR;
    private boolean isBlackRace;
    private String classification;

    public RenalFunction() {
    }
    public RenalFunction(double serumCreatinine, double GFR, boolean isBlackRace, String classification) {
        this.serumCreatinine = serumCreatinine;
        this.GFR = GFR;
        this.isBlackRace = isBlackRace;
        this.classification = classification;
    }

    public double getSerumCreatinine() {
        return serumCreatinine;
    }

    public void setSerumCreatinine(double serumCreatinine) {
        this.serumCreatinine = serumCreatinine;
    }

    public double getGFR() {
        return GFR;
    }

    public void setGFR(double GFR) {
        this.GFR = GFR;
    }

    public boolean isBlackRace() {
        return isBlackRace;
    }

    public void setBlackRace(boolean blackRace) {
        isBlackRace = blackRace;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
