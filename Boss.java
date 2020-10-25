// Name: Marvin Dale
// ID  : 18362583

// Boss class derived from Employee.

import org.joda.money.Money;
import java.time.LocalDate;

public final class Boss extends Employee {

    private Money weeklySalary;
    private int   idNum; // unique id number

    // constructor for class Boss
    public Boss(String first, String last, Money salary, LocalDate date, Money taxCredits) {
        super(first, last, date, taxCredits); // call superclass constructor
        setWeeklySalary(salary);
    }

    // set Boss's salary
    public void setWeeklySalary(Money salary) {
        Money zero   = Money.parse("EUR 0");
        weeklySalary = (salary.isGreaterThan(zero) ? salary : zero);
    }

    // get Boss's pay
    public Money earnings(taxCalculator calc) throws LowWageException {
        // set limit to throw exception
        Money min = Money.parse("EUR 100");

        if (weeklySalary.isLessThan(min)) {
            throw new LowWageException(getFirstName() + " " + getLastName()
                    +" is earning " + weeklySalary + " a week" + "\n"
                    + "Each employee must earn at least " + min + " a week \n");
        }

        Money netSalary = calc.calculateTax(weeklySalary, getTaxCredits());

        return netSalary;
    }

    // get String representation of Boss's name
    public String toString() {
        return "Boss: " + super.toString();
    }
} // end class Boss
