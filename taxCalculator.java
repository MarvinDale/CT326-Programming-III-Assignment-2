// Name: Marvin Dale
// ID  : 18362583

import org.joda.money.Money;

public interface taxCalculator {
    // Returns the tax owed for a particular time period based on the earnings and the tax credits
    public Money calculateTax(Money earnings, Money taxCredits);
}