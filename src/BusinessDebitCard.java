import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BusinessDebitCard extends DebitCard {
    public BusinessDebitCard(BigDecimal balance) {
        super(balance);
    }

    protected BigDecimal cashBackRatio = BigDecimal.valueOf(0.05); // Кэшбэк 5%
    private BigDecimal cashBackBalance = new BigDecimal(0);
    private final BigDecimal cashBackLowLimited = BigDecimal.valueOf(5000);

    @Override
    public boolean pay(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            amountSpent = amountSpent.add(amount);
            balance = balance.subtract(amount);
            if (amountSpent.compareTo(cashBackLowLimited) >= 0)
                cashBackBalance = cashBackBalance.add(amount.multiply(cashBackRatio));
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal getAvailableFunds() {
        return super.getBalance().add(getCashBackBalance());
    }


    public BigDecimal getCashBackBalance() {
        DecimalFormat format = new DecimalFormat("0.##");
        return BigDecimal.valueOf(Long.parseLong(format.format(cashBackBalance)));
    }
}
