import java.math.BigDecimal;
import java.text.DecimalFormat;

public class YoungPeopleCreditCard extends CreditCard {
    public YoungPeopleCreditCard(BigDecimal balance, BigDecimal creditLimit, BigDecimal creditBalance) {
        super(balance, creditLimit, creditBalance);
    }

    protected BigDecimal cumulativeInterest = (BigDecimal.valueOf(0.005));

    @Override
    public void replenishment(BigDecimal amount) {
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        BigDecimal percentageОfКeplenishment = amount.multiply(cumulativeInterest);



        amount = amount.add(BigDecimal.valueOf(Long.parseLong(decimalFormat.format(percentageОfКeplenishment))));

        balance = getBalance();
        if (creditBalance.compareTo(creditLimit) < 0) {
            BigDecimal dept = creditLimit.subtract(creditBalance);
            if (amount.compareTo(dept) > 0) {
                amount = amount.subtract(dept);
                creditBalance = creditBalance.add(dept);
                balance = balance.add(amount);
            } else {
                creditBalance = creditBalance.add(amount);
            }
        }
        else {
            balance = balance.add(amount);
        }
    }
}
