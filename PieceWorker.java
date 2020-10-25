// Name: Marvin Dale
// ID  : 18362583

// PieceWorker class derived from Employee

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.time.LocalDate;

public final class PieceWorker extends Employee {

    private Money wagePerPiece; // wage per piece output
    private int   quantity;     // output for week
    private int   idNum;        // unique id number

    // constructor for class PieceWorker
    public PieceWorker(String first, String last, LocalDate date,
            Money wage, int numberOfItems, Money taxCredits) {
        super(first, last, date, taxCredits); // call superclass constructor
        setWage(wage);
        setQuantity(numberOfItems);
    }

    // set PieceWorker's wage
    public void setWage(Money wage) {
        Money zero   = Money.parse("EUR 0");
        wagePerPiece = (wage.isGreaterThan(zero) ? wage : zero);
    }

    // set number of items output
    public void setQuantity(int numberOfItems) {
        quantity = (numberOfItems > 0 ? numberOfItems : 0);
    }

    // determine PieceWorker's earnings
    public Money earnings (taxCalculator calc) throws LowWageException {
        Money grossEarnings = wagePerPiece.multipliedBy(quantity);
        Money min           = Money.parse("EUR 100"); // set limit to throw exception

        //throw exception if the total earnings are below EUR 100
        if (grossEarnings.isLessThan(min)){
            throw new LowWageException(getFirstName() + " " + getLastName()
                    +" is earning " + grossEarnings + " a week" + "\n"
                    + "Each employee must earn at least " + min + " a week \n");
        }

        //pass grossEarnings and tax credits to be calculated and returned
        Money netEarnings = calc.calculateTax(grossEarnings, getTaxCredits());

        return netEarnings;
    }
    public String toString() {
        return "Piece worker: " + super.toString();
    }
}
