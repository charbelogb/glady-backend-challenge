package fr.glady.challenge.backend;

import fr.glady.challenge.backend.model.Company;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest {

    @Test
    public void createCompany_OK() throws Exception {
        String name = "Tesla";
        double balance = 5000;
        Company company = new Company(name, balance);
        assertNotNull(company);
        assertEquals(company.getName(), name);
        assertEquals(company.getBalance(), balance);
        assertNotNull(company.getDeposits());
        assertEquals(company.getDeposits().size(), 0);
    }

    @Test
    public void createCompany_should_raise_exception_when_balance_is_negative() {
        Exception exception = assertThrows(Exception.class, () -> new Company("Tesla", -5000));
        String expectedMessage = "Company's balance should not be less than 0";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}
