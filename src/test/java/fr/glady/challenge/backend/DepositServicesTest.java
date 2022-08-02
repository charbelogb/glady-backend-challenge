package fr.glady.challenge.backend;

import fr.glady.challenge.backend.model.Company;
import fr.glady.challenge.backend.model.DepositType;
import fr.glady.challenge.backend.model.User;
import fr.glady.challenge.backend.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DepositServicesTest {

    private static String USER_NAME = "Tassi Hangbe";
    private static String COMPANY_NAME = "Tesla";
    private static double COMPANY_BALANCE = 5000.0;

    @Test
    public void gift_OK() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        DepositServices.gift(tesla, 1000, tassi); // Tesla makes a $1000 git deposit to Tassi's account

        assertEquals(1, tassi.getDeposits().size()); // Tassi got 1 deposit
        assertEquals(1, tesla.getDeposits().size()); // Tesla made 1 deposit
        assertEquals(tesla.getDeposits().get(0), tassi.getDeposits().get(0)); // Tesla and Tassi both has the deposit informations
        assertEquals(DepositType.GIFT, tassi.getDeposits().get(0).getType()); // Deposit type is GIFT
        assertEquals(tesla.getName(), tassi.getDeposits().get(0).getCompany().getName()); // Tassi's deposit is from Tesla
        assertEquals(tassi.getFullName(), tesla.getDeposits().get(0).getUser().getFullName()); // Tesla's deposit is for Tassi
        assertEquals(1000, tassi.getBalance()); // Tassi's balance is 1000
        assertEquals(4000, tesla.getBalance()); // Tesla's balance is now 4000
        assertEquals(LocalDate.now().plusDays(364), tassi.getDeposits().get(0).getExpirationDate()); // The deposit has a 365 days lifespan
    }

    @Test
    public void meal_OK() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        DepositServices.meal(tesla, 1000, tassi); // Tesla makes a $1000 git deposit to Tassi's account

        assertEquals(1, tassi.getDeposits().size()); // Tassi got 1 deposit
        assertEquals(1, tesla.getDeposits().size()); // Tesla made 1 deposit
        assertEquals(tesla.getDeposits().get(0), tassi.getDeposits().get(0)); // Tesla and Tassi both has the deposit informations
        assertEquals(DepositType.MEAL, tassi.getDeposits().get(0).getType()); // Deposit type is MEAL
        assertEquals(tesla.getName(), tassi.getDeposits().get(0).getCompany().getName()); // Tassi's deposit is from Tesla
        assertEquals(tassi.getFullName(), tesla.getDeposits().get(0).getUser().getFullName()); // Tesla's deposit is for Tassi
        assertEquals(1000, tassi.getBalance()); // Tassi's balance is 1000
        assertEquals(4000, tesla.getBalance()); // Tesla's balance is now 4000

        LocalDate expectedExpirationDate = DateUtils.getEndOfNextFebruary(tassi.getDeposits().get(0).getDepositDate());

        assertEquals(expectedExpirationDate, tassi.getDeposits().get(0).getExpirationDate()); // The deposit has expires at the end of February of the year following the distribution date
    }

    @Test
    public void gift_should_raise_exception_when_amount_is_negative() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        Exception exception = assertThrows(Exception.class, () -> DepositServices.gift(tesla, -1000, tassi));
        String expectedMessage = "Please set a deposit amount greater than 0";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void gift_should_raise_exception_when_amount_is_zero() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        Exception exception = assertThrows(Exception.class, () -> DepositServices.gift(tesla, 0, tassi));
        String expectedMessage = "Please set a deposit amount greater than 0";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void meal_should_raise_exception_when_amount_is_negative() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        Exception exception = assertThrows(Exception.class, () -> DepositServices.meal(tesla, -1000, tassi));
        String expectedMessage = "Please set a deposit amount greater than 0";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void meal_should_raise_exception_when_amount_is_zero() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        Exception exception = assertThrows(Exception.class, () -> DepositServices.meal(tesla, 0, tassi));
        String expectedMessage = "Please set a deposit amount greater than 0";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void gift_should_raise_exception_when_balance_is_unsufficient() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        Exception exception = assertThrows(Exception.class, () -> DepositServices.gift(tesla, 5500, tassi));
        String expectedMessage = "Your balance is unsufficient to make the deposit";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void meal_should_raise_exception_when_balance_is_unsufficient() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        Exception exception = assertThrows(Exception.class, () -> DepositServices.meal(tesla, 5500, tassi));
        String expectedMessage = "Your balance is unsufficient to make the deposit";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}
