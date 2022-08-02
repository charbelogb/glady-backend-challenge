package fr.glady.challenge.backend.model;

public enum DepositType {

    GIFT("gift"), MEAL("meal");

    public final String value;

    DepositType(String value) {
        this.value = value;
    }
}
