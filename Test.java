// Name: Marvin Dale
// ID  : 18362583

// Driver for Employee hierarchy

import org.joda.money.Money;
// Java core packages
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;

// Java extension packages
import javax.swing.JOptionPane;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.test1(); // test with no exceptions
    }

    public void test1() {
        // test Employee hierarchy
            ArrayList<Employee> employees = new ArrayList<>();

            //workers instantiated with their weekly earnings
            Boss boss = new Boss("John", "Smith", Money.parse("EUR 800"),
                    LocalDate.of(2000, 1, 3), Money.parse("EUR 1200"));

            CommissionWorker commissionWorker =
                    new CommissionWorker(
                            "Sue", "Jones", LocalDate.of(2017, 1, 13),
                            Money.parse("EUR 400"), Money.parse("EUR 3.0"), 150, Money.parse("EUR 450"));

            PieceWorker pieceWorker =
                    new PieceWorker("Bob", "Lewis", LocalDate.of(2020, 4, 15),
                            Money.parse("EUR 2.5"), 200, Money.parse("EUR 380"));

            HourlyWorker hourlyWorker =
                    new HourlyWorker("Karen", "Price", LocalDate.of(2016, 9, 1),
                            Money.parse("EUR 13.75"), 40, Money.parse("EUR 610"));

            //add workers to arraylist
            employees.add(boss);
            employees.add(commissionWorker);
            employees.add(pieceWorker);
            employees.add(hourlyWorker);

            // Strings for each method used
            String usingClass      = "";
            String usingInnerClass = "";
            String usingLambda     = "";

            // append output of each method to strings
            usingClass      += handlePayrollWithClass(employees);
            usingInnerClass += handlePayrollWithInner(employees);
            usingLambda     += handlePayrollWithLambda(employees);

            // concatenate all output strings together
            String output = "***** Implemented using a Class *****\n" + usingClass
                    + "\n ***** Implemented using an Anonymous Inner class *****\n" + usingInnerClass
                    + "\n ***** Implemented using a Lambda Function *****\n" + usingLambda;

            // display output
            JOptionPane.showMessageDialog(null, output,
                    "Monthly Staff Payroll",
                    JOptionPane.INFORMATION_MESSAGE);

            System.exit(0);
    }

    //implementation of taxCalculator using a class
    public static String handlePayrollWithClass(ArrayList<Employee> employees) {
         Tax tax = new Tax();
        String output = "";

        Money bonus;
        for (Employee emp: employees) {
            bonus = getBonus(emp);
            //try to get employee earnings
            try {
                // calculate tax using tax class
                output += emp.toString() + " " + emp.earnings(tax).multipliedBy(
                        4, RoundingMode.FLOOR).plus(bonus) + "\n";
                // catch LowWageException
            } catch (LowWageException exception) {
                output += exception.getMessage();
                exception.printStackTrace();
            }
        }
        return output;
    }

    //implementation of taxCalculator using an anonymous inner class
    public static String handlePayrollWithInner(ArrayList<Employee> employees) {
        String output = "";
        Money  bonus;

        for (Employee emp: employees) {
            bonus = getBonus(emp);
            //try to get employee earnings
            try {
                // calculate tax with inner class
                output += emp.toString() + " " + emp.earnings(new taxCalculator() {
                    @Override
                    public Money calculateTax(Money earnings, Money taxCredits) {
                        Money monthlyTaxCredit =
                                taxCredits.dividedBy(12, RoundingMode.valueOf(2));
                        Money taxOwed =
                                earnings.multipliedBy(0.2, RoundingMode.valueOf(2))
                                        .minus(monthlyTaxCredit);

                        Money netEarnings = earnings.minus(taxOwed);

                        return netEarnings;
                    }
                }).multipliedBy(4, RoundingMode.FLOOR).plus(bonus) + "\n";
                // catch LowWageException
            } catch (LowWageException exception) {
                output += exception.getMessage();
                exception.printStackTrace();
            }
        }
        return output;
    }

    //implementation of taxCalculator using a lambda function
    public static String handlePayrollWithLambda(ArrayList<Employee> employees) {
        String output = "";
        Money  bonus;

        for (Employee emp: employees) {
            bonus = getBonus(emp);
            //try to get employee earnings
            try {
                //calculate tax with lambda function
                output += emp.toString() + " " + emp.earnings((earnings, taxCredits) -> {

                    Money monthlyTaxCredit =
                            taxCredits.dividedBy(12, RoundingMode.valueOf(2));
                    Money taxOwed =
                            earnings.multipliedBy(0.2, RoundingMode.valueOf(2))
                                    .minus(monthlyTaxCredit);
                    Money netEarnings = earnings.minus(taxOwed);

                    return netEarnings;})
                        .multipliedBy(4, RoundingMode.FLOOR).plus(bonus) + "\n";
                // catch LowWageException
            } catch (LowWageException exception) {
                output += exception.getMessage();
                exception.printStackTrace();
            }
        }
        return output;
    }

    // calculate bonus for the given employee
    public static Money getBonus(Employee emp) {
        int thisYear  = LocalDate.now().getYear();
        int yearsAtCompany = thisYear - emp.getJoinDate().getYear();

        //Employees at the company 5+ years get a bonus
        Money bonus =
                (yearsAtCompany > 5) ? Money.parse("EUR 200"): Money.parse("EUR 0");

        return  bonus;
    }
} // end class Test
