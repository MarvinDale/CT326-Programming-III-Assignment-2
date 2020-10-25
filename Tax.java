// Name: Marvin Dale
// ID  : 18362583

import org.joda.money.Money;
import java.math.RoundingMode;

public class Tax implements taxCalculator {
    @Override
    public Money calculateTax(Money earnings, Money taxCredits) {
        // Split tax credits over each month
        Money monthlyTaxCredit =
                taxCredits.dividedBy(12, RoundingMode.valueOf(2));
        //tax owed is 20% of the gross earning minus the tax credits
        Money taxOwed =
                earnings.multipliedBy(0.2, RoundingMode.valueOf(2))
                        .minus(monthlyTaxCredit);

        Money netEarnings = earnings.minus(taxOwed);

        return netEarnings;
    }
}
