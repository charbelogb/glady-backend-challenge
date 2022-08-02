package fr.glady.challenge.backend;

import fr.glady.challenge.backend.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DepositServices {

    /**
     * Dstribute Gift deposits
     * @param company
     * @param amount
     * @param user
     * @throws Exception
     */
    public static void gift(Company company, double amount, User user) throws Exception {
        checkAmounts(company, amount);
        operateDeposit(DepositType.GIFT, company, amount, user);
    }

    /**
     * Distribute meal deposits
     * @param company
     * @param amount
     * @param user
     * @throws Exception
     */
    public static void meal(Company company, double amount, User user) throws Exception {
        checkAmounts(company, amount);
        operateDeposit(DepositType.MEAL, company, amount, user);
    }

    /**
     * User's balance is determined by the sum of the amount of user's valid deposits.
     * @return user balance
     */
    public static double getUserBalance(User user) {
        if (user != null) {
            List<Deposit> validDeposit = user.getDeposits().stream().filter(deposit -> deposit.getExpirationDate().isAfter(LocalDate.now())).collect(Collectors.toList());
            List<Double> depositAmounts = validDeposit.stream().map(Deposit::getAmount).collect(Collectors.toList());
            return depositAmounts.stream().reduce(0.0, (a, b) -> a + b);
        }
        return 0;
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
