package fr.glady.challenge.backend.model;

import fr.glady.challenge.backend.utils.DateUtils;

import java.time.LocalDate;

public class MealDeposit extends Deposit {

    public MealDeposit(Company company, double amount, User user, LocalDate depositDate) throws Exception {
        super(DepositType.MEAL, company, amount, user, depositDate);
    }

    @Override
    public LocalDate getExpirationDate() {
        return DateUtils.getEndOfNextFebruary(depositDate);
    }
}
