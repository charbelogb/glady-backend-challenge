package fr.glady.challenge.backend.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String fullName;
    private List<Deposit> deposits = new ArrayList<>();

    public User(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

}
