package fr.glady.challenge.backend;

import fr.glady.challenge.backend.model.*;

import java.time.LocalDate;

public class DepositServices {

    public static void gift(Company company, double amount, User user) throws Exception {
        checkAmounts(company, amount);
        operateDeposit(DepositType.GIFT, company, amount, user);
    }

    public static void meal(Company company, double amount, User user) throws Exception {
        checkAmounts(company, amount);
        operateDeposit(DepositType.MEAL, company, amount, user);
    }

    /**
     * Check if user's balance is sufficient and deposit amount is valid
     * @param company
     * @param depositAmount
     * @throws Exception
     */
    private static void checkAmounts(Company company, double depositAmount) throws Exception {
        if (depositAmount <= 0) {
            throw new Exception("Please set a deposit amount greater than 0");
        }
        if (company.getBalance() < depositAmount) {
            throw new Exception("Your balance is unsufficient to make the deposit");
        }
    }

    private static void operateDeposit(DepositType depositType, Company company, double amount, User user) throws Exception {
        Deposit deposit;
        if (depositType == DepositType.GIFT) {
            deposit = new GiftDeposit(company, amount, user, LocalDate.now());
        } else {
            deposit = new MealDeposit(company, amount, user, LocalDate.now());
        }
        company.setBalance(company.getBalance() - amount);
        user.getDeposits().add(deposit);
        company.getDeposits().add(deposit);
    }

}
