package fr.glady.challenge.backend;

import fr.glady.challenge.backend.model.Company;
import fr.glady.challenge.backend.model.DepositType;
import fr.glady.challenge.backend.model.MealDeposit;
import fr.glady.challenge.backend.model.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Month;
import static org.junit.jupiter.api.Assertions.*;

public class MealDepositTest {

    private static String USER_NAME = "Tassi Hangbe";
    private static String COMPANY_NAME = "Tesla";
    private static double COMPANY_BALANCE = 5000.0;

    @Test
    public void createMealDeposit_OK() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        MealDeposit mealDeposit = new MealDeposit(tesla, 1000, tassi, LocalDate.now());
        assertNotNull(mealDeposit);
    }

    @Test
    public void createMealDeposit_should_raise_exception_when_amount_is_not_greater_than_zero() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        Exception exception = assertThrows(Exception.class, () -> new MealDeposit(tesla, -1000, tassi, LocalDate.now()));
        String expectedMessage = "Deposit amount should be greater than 0";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void getDepositType_OK() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        MealDeposit mealDeposit = new MealDeposit(tesla, 1000, tassi, LocalDate.now());
        assertEquals(mealDeposit.getType(), DepositType.MEAL);
    }

    @Test
    public void getCompany_OK() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        MealDeposit mealDeposit = new MealDeposit(tesla, 1000, tassi, LocalDate.now());
        assertNotNull(mealDeposit.getCompany());
        assertEquals(mealDeposit.getCompany().getName(), tesla.getName());
        assertEquals(mealDeposit.getCompany().getBalance(), tesla.getBalance());
    }

    @Test
    public void getAmount_OK() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        MealDeposit mealDeposit = new MealDeposit(tesla, 1000, tassi, LocalDate.now());
        assertEquals(mealDeposit.getAmount(), 1000);
    }

    @Test
    public void getDepositDate_OK() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        LocalDate depositDate = LocalDate.now();
        MealDeposit mealDeposit = new MealDeposit(tesla, 1000, tassi, depositDate);
        assertEquals(mealDeposit.getDepositDate(), depositDate);
    }

    @Test
    public void getUser_OK() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        LocalDate depositDate = LocalDate.now();
        MealDeposit mealDeposit = new MealDeposit(tesla, 1000, tassi, depositDate);
        assertNotNull(mealDeposit.getUser());
        assertEquals(mealDeposit.getUser().getFullName(), tassi.getFullName());
        assertEquals(0, DepositServices.getBalance(mealDeposit.getUser()));
        assertEquals(mealDeposit.getUser().getDeposits().size(), 0);
    }

    @Test
    public void getExpirationDate_OK() throws Exception {
        Company tesla = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User tassi = new User(USER_NAME);
        MealDeposit mealDeposit = new MealDeposit(tesla, 1000, tassi, LocalDate.of(2020, Month.JANUARY, 1));
        LocalDate expectedExpirationDate = LocalDate.of(2021, Month.FEBRUARY, 28);
        assertEquals(mealDeposit.getExpirationDate(), expectedExpirationDate);
    }

}
