package com.medicalcenterapi.model.scores;

public class GlasgowComaScale {
    private boolean EOREyesSpontaneous;
    private boolean EOREyesVerbal;
    private boolean EOREyesPain;
    private boolean EORNoResponse;
    private boolean VROriented;
    private boolean VRConfused;
    private boolean VRInappropiate;
    private boolean VRIncomprenhensible;
    private boolean VRNoResponse;
    private boolean MRObeys;
    private boolean MRPurposefulPain;
    private boolean MRWithdrawsPain;
    private boolean MRFlexionPain;
    private boolean MRExtensionPain;
    private boolean MRNoResponse;

    public GlasgowComaScale() {
    }

    public GlasgowComaScale(boolean EOREyesSpontaneous, boolean EOREyesVerbal, boolean EOREyesPain, boolean EORNoResponse, boolean VROriented, boolean VRConfused, boolean VRInappropiate, boolean VRIncomprenhensible, boolean VRNoResponse, boolean MRObeys, boolean MRPurposefulPain, boolean MRWithdrawsPain, boolean MRFlexionPain, boolean MRExtensionPain, boolean MRNoResponse) {
        this.EOREyesSpontaneous = EOREyesSpontaneous;
        this.EOREyesVerbal = EOREyesVerbal;
        this.EOREyesPain = EOREyesPain;
        this.EORNoResponse = EORNoResponse;
        this.VROriented = VROriented;
        this.VRConfused = VRConfused;
        this.VRInappropiate = VRInappropiate;
        this.VRIncomprenhensible = VRIncomprenhensible;
        this.VRNoResponse = VRNoResponse;
        this.MRObeys = MRObeys;
        this.MRPurposefulPain = MRPurposefulPain;
        this.MRWithdrawsPain = MRWithdrawsPain;
        this.MRFlexionPain = MRFlexionPain;
        this.MRExtensionPain = MRExtensionPain;
        this.MRNoResponse = MRNoResponse;
    }

    public boolean isEOREyesSpontaneous() {
        return EOREyesSpontaneous;
    }

    public void setEOREyesSpontaneous(boolean EOREyesSpontaneous) {
        this.EOREyesSpontaneous = EOREyesSpontaneous;
    }

    public boolean isEOREyesVerbal() {
        return EOREyesVerbal;
    }

    public void setEOREyesVerbal(boolean EOREyesVerbal) {
        this.EOREyesVerbal = EOREyesVerbal;
    }

    public boolean isEOREyesPain() {
        return EOREyesPain;
    }

    public void setEOREyesPain(boolean EOREyesPain) {
        this.EOREyesPain = EOREyesPain;
    }

    public boolean isEORNoResponse() {
        return EORNoResponse;
    }

    public void setEORNoResponse(boolean EORNoResponse) {
        this.EORNoResponse = EORNoResponse;
    }

    public boolean isVROriented() {
        return VROriented;
    }

    public void setVROriented(boolean VROriented) {
        this.VROriented = VROriented;
    }

    public boolean isVRConfused() {
        return VRConfused;
    }

    public void setVRConfused(boolean VRConfused) {
        this.VRConfused = VRConfused;
    }

    public boolean isVRInappropiate() {
        return VRInappropiate;
    }

    public void setVRInappropiate(boolean VRInappropiate) {
        this.VRInappropiate = VRInappropiate;
    }

    public boolean isVRIncomprenhensible() {
        return VRIncomprenhensible;
    }

    public void setVRIncomprenhensible(boolean VRIncomprenhensible) {
        this.VRIncomprenhensible = VRIncomprenhensible;
    }

    public boolean isVRNoResponse() {
        return VRNoResponse;
    }

    public void setVRNoResponse(boolean VRNoResponse) {
        this.VRNoResponse = VRNoResponse;
    }

    public boolean isMRObeys() {
        return MRObeys;
    }

    public void setMRObeys(boolean MRObeys) {
        this.MRObeys = MRObeys;
    }

    public boolean isMRPurposefulPain() {
        return MRPurposefulPain;
    }

    public void setMRPurposefulPain(boolean MRPurposefulPain) {
        this.MRPurposefulPain = MRPurposefulPain;
    }

    public boolean isMRWithdrawsPain() {
        return MRWithdrawsPain;
    }

    public void setMRWithdrawsPain(boolean MRWithdrawsPain) {
        this.MRWithdrawsPain = MRWithdrawsPain;
    }

    public boolean isMRFlexionPain() {
        return MRFlexionPain;
    }

    public void setMRFlexionPain(boolean MRFlexionPain) {
        this.MRFlexionPain = MRFlexionPain;
    }

    public boolean isMRExtensionPain() {
        return MRExtensionPain;
    }

    public void setMRExtensionPain(boolean MRExtensionPain) {
        this.MRExtensionPain = MRExtensionPain;
    }

    public boolean isMRNoResponse() {
        return MRNoResponse;
    }

    public void setMRNoResponse(boolean MRNoResponse) {
        this.MRNoResponse = MRNoResponse;
    }
}
