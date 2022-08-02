package fr.glady.challenge.backend;

import fr.glady.challenge.backend.model.Company;
import fr.glady.challenge.backend.model.User;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {

        try {

            // GIFT
            Company tesla = new Company("Tesla", 2000);
            System.out.println("Tesla initial balance is : " + tesla.getBalance());
            User marlene = new User("Marlène");
            System.out.println("Marlène initial balance is " + marlene.getBalance());
            DepositServices.gift(tesla, -10, marlene);

            System.out.println(marlene.getFullName() + " got " + marlene.getDeposits().size() + " deposit from : " + tesla.getName());
            System.out.println("Deposit 1 will expire on : " + marlene.getDeposits().get(0).getExpirationDate());
            System.out.println(marlene.getFullName() + " total balance is : " + marlene.getBalance());
            System.out.println(tesla.getName() + " new balance is : " + tesla.getBalance());

            // MEAL
            Company apple = new Company("Apple", 1000);
            System.out.println("Apple initial balance is : " + apple.getBalance());
            User charbel = new User("Charbel");
            System.out.println("Charbel initial balance is " + charbel.getBalance());
            DepositServices.meal(apple, 1000, charbel);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
