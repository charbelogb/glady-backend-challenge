package fr.glady.challenge.backend;

import fr.glady.challenge.backend.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private static String USER_NAME = "Bio Guera";

    @Test
    public void createUser_OK() {
        User user = new User(USER_NAME);
        assertNotNull(user);
    }

    @Test
    public void getFullName_OK() {
        User user = new User(USER_NAME);
        assertEquals(user.getFullName(), USER_NAME);
    }

    @Test
    public void getBalance_OK() {
        User user = new User(USER_NAME);
        assertEquals(user.getBalance(), 0.0);
    }

    @Test
    public void getDeposits_OK() {
        User user = new User(USER_NAME);
        assertNotNull(user.getDeposits());
        assertEquals(user.getDeposits().size(), 0.0);
    }

}
