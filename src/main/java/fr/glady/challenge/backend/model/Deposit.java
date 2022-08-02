package fr.glady.challenge.backend.model;

import java.time.LocalDate;

public abstract class Deposit {

    protected DepositType type;
    protected Company company;
    protected double amount;
    protected LocalDate depositDate;
    protected User user;

    protected Deposit(DepositType type, Company company, double amount, User user, LocalDate depositDate) throws Exception {
        if (amount <= 0) {
            throw new Exception("Deposit amount should be greater than 0");
        }
        this.type = type;
        this.company = company;
        this.amount = amount;
        this.user = user;
        this.depositDate = depositDate;
    }

    public DepositType getType() {
        return type;
    }

    public Company getCompany() {
        return company;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDepositDate() {
        return depositDate;
    }

    public User getUser() {
        return user;
    }

    public abstract LocalDate getExpirationDate();

}
