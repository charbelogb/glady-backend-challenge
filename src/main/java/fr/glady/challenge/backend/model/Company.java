package fr.glady.challenge.backend.model;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private String name;
    private double balance;
    private List<Deposit> deposits = new ArrayList<>();

    public Company(String name, double balance) throws Exception {
        this.name = name;
        if (balance < 0) {
            throw new Exception("Company's balance should not be less than 0");
        }
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

}
