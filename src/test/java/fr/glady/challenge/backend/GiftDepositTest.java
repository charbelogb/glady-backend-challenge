package fr.glady.challenge.backend;

import fr.glady.challenge.backend.model.Company;
import fr.glady.challenge.backend.model.DepositType;
import fr.glady.challenge.backend.model.GiftDeposit;
import fr.glady.challenge.backend.model.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GiftDepositTest {

    private static String USER_NAME = "Bio Guera";
    private static String COMPANY_NAME = "Apple";
    private static double COMPANY_BALANCE = 5000.0;

    @Test
    public void createGiftDeposit_OK() throws Exception {
        Company apple = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User guera = new User(USER_NAME);
        GiftDeposit giftDeposit = new GiftDeposit(apple, 1000, guera, LocalDate.now());
        assertNotNull(giftDeposit);
    }

    @Test
    public void createGiftDeposit_should_raise_exception_when_amount_is_not_greater_than_zero() throws Exception {
        Company apple = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User guera = new User(USER_NAME);
        Exception exception = assertThrows(Exception.class, () -> new GiftDeposit(apple, -1000, guera, LocalDate.now()));
        String expectedMessage = "Deposit amount should be greater than 0";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void getDepositType_OK() throws Exception {
        Company apple = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User guera = new User(USER_NAME);
        GiftDeposit giftDeposit = new GiftDeposit(apple, 1000, guera, LocalDate.now());
        assertEquals(DepositType.GIFT, giftDeposit.getType());
    }

    @Test
    public void getCompany_OK() throws Exception {
        Company apple = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User guera = new User(USER_NAME);
        GiftDeposit giftDeposit = new GiftDeposit(apple, 1000, guera, LocalDate.now());
        assertNotNull(giftDeposit.getCompany());
        assertEquals(apple.getName(), giftDeposit.getCompany().getName());
        assertEquals(apple.getBalance(), giftDeposit.getCompany().getBalance());
    }

    @Test
    public void getAmount_OK() throws Exception {
        Company apple = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User guera = new User(USER_NAME);
        GiftDeposit giftDeposit = new GiftDeposit(apple, 1000, guera, LocalDate.now());
        assertEquals(1000, giftDeposit.getAmount());
    }

    @Test
    public void getDepositDate_OK() throws Exception {
        Company apple = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User guera = new User(USER_NAME);
        LocalDate depositDate = LocalDate.now();
        GiftDeposit giftDeposit = new GiftDeposit(apple, 1000, guera, depositDate);
        assertEquals(depositDate, giftDeposit.getDepositDate());
    }

    @Test
    public void getUser_OK() throws Exception {
        Company apple = new Company(COMPANY_NAME, COMPANY_BALANCE);
        User guera = new User(USER_NAME);
        LocalDate depositDate = LocalDate.now();
        GiftDeposit giftDeposit = new GiftDeposit(apple, 1000, guera, depositDate);
        assertNotNull(giftDeposit.getUser());
        assertEquals(guera.getFullName(), giftDeposit.getUser().getFullName());
        assertEquals(0, DepositServices.getBalance(giftDeposit.getUser()));
        assertEquals(0, giftDeposit.getUser().getDeposits().size());
    }

}
