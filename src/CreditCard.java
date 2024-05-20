import java.math.BigDecimal;

public class CreditCard extends BankCard {
    protected BigDecimal creditLimit;
    protected BigDecimal creditBalance;

    public CreditCard(BigDecimal balance, BigDecimal creditLimit, BigDecimal creditBalance) {
        super(balance);
        this.creditLimit = creditLimit;
        this.creditBalance = creditBalance;
    }

    @Override
    public void replenishment(BigDecimal amount) {
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
        } else {
            balance = balance.add(amount);
        }
    }

    @Override
    public BigDecimal getAvailableFunds() {
        BigDecimal balance = getBalance();
        return balance.add(creditBalance);

    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    @Override
    public boolean pay(BigDecimal amount) {
        if (amount.compareTo(getAvailableFunds()) > 0) {
            return false;
        }

        if (balance.compareTo(amount) <= 0) {
            amount = amount.subtract(balance);
            balance = BigDecimal.valueOf(0);
            creditBalance = creditBalance.subtract(amount);

        } else {
            balance = balance.subtract(amount);
        }
        return true;
    }
}
