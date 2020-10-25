// Name: Marvin Dale
// ID  : 18362583

// Abstract base class Employee.

import org.joda.money.Money;

import java.time.LocalDate;

public abstract class Employee {

    private String    firstName;
    private String    lastName;
    private LocalDate joinDate;
    private Money     taxCredits;
    private int       idNum;
    public static int id = 0;

    // constructor
    public Employee(String first, String last, LocalDate date, Money taxCredits) {
        firstName = first;
        lastName  = last;
        joinDate  = date;
        id++;
        idNum     = id;
        this.taxCredits = taxCredits;
    }

    // get first name
    public String getFirstName() {
        return firstName;
    }

    // get last name
    public String getLastName() { return lastName; }

    //get join date
    public LocalDate getJoinDate() { return joinDate;}

    public Money getTaxCredits() {
        return taxCredits;
    }

    public String toString() {
        return firstName + ' ' + lastName + " (ID: " + idNum + ")";
    }

    public abstract Money earnings(taxCalculator calc) throws LowWageException;
}
