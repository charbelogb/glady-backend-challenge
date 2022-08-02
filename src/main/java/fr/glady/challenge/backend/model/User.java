package fr.glady.challenge.backend.model;

import fr.glady.challenge.backend.model.Deposit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {

    private String fullName;
    private List<Deposit> deposits = new ArrayList<>();

    public User(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    /**
     * User's balance is determined by the sum of the amount of user's valid deposits.
     * @return user balance
     */
    public double getBalance() {
        List<Deposit> validDeposit = deposits.stream().filter(deposit -> deposit.getExpirationDate().isAfter(LocalDate.now())).collect(Collectors.toList());
        List<Double> depositAmounts = validDeposit.stream().map(Deposit::getAmount).collect(Collectors.toList());
        return depositAmounts.stream().reduce(0.0, (a, b) -> a + b);
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

}
