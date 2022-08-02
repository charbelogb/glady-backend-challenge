package fr.glady.challenge.backend.model;

import java.time.LocalDate;

public class GiftDeposit extends Deposit {

    public GiftDeposit(Company company, double amount, User user, LocalDate depositDate) throws Exception {
        super(DepositType.GIFT, company, amount, user, depositDate);
    }

    @Override
    public LocalDate getExpirationDate() {
        return depositDate.plusDays(364); // Gift deposit lifespan is 365 days.
    }
}
