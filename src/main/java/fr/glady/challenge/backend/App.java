package fr.glady.challenge.backend;

import fr.glady.challenge.backend.model.Company;
import fr.glady.challenge.backend.model.User;
import java.time.LocalDate;

public class App {

    public static void main( String[] args ) {

        double balance = 2000;
        double amount = 1000;

        try {
            // GIFT
            System.out.println("******* GIFT SERVICE *******");
            System.out.println("The following event are happening today : " + LocalDate.now());
            Company tesla = new Company("Tesla", balance);
            System.out.println(tesla.getName() + " company's initial balance is : $" + tesla.getBalance());
            User marlene = new User("Marlène");
            System.out.println(marlene.getFullName() + "'s initial balance is : $" + DepositServices.getUserBalance(marlene));
            DepositServices.gift(tesla, amount, marlene);
            System.out.println(tesla.getName() + " distributed a $" + amount + " " + tesla.getDeposits().get(0).getType().value + " deposit for " + marlene.getFullName());
            System.out.println(marlene.getFullName() + "'s balance is now : $" + DepositServices.getUserBalance(marlene));
            System.out.println(marlene.getFullName() + " has until " + marlene.getDeposits().get(0).getExpirationDate() + " to spend her gift");
            System.out.println("... and " + tesla.getName() + "'s balance is now : $" + tesla.getBalance() + "\n");

            // MEAL
            System.out.println("******* MEAL SERVICE *******");
            DepositServices.meal(tesla, amount, marlene);
            System.out.println(tesla.getName() + " now distributed a $" + amount + " " + tesla.getDeposits().get(1).getType().value + " deposit for " + marlene.getFullName());
            System.out.println(marlene.getFullName() + "'s balance is now : $" + DepositServices.getUserBalance(marlene));
            System.out.println(marlene.getFullName() + " has until " + marlene.getDeposits().get(1).getExpirationDate() + " to spend her gift");
            System.out.println("... and " + tesla.getName() + "'s balance is now : $" + tesla.getBalance() + "\n");

            // UNALLOWED GIFT
            User kelvin = new User("Kelvin");
            DepositServices.gift(tesla, amount, kelvin); // Tesla tries to distribute a new gift with an unsufficient balance
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
