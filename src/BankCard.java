import java.math.BigDecimal;

public abstract class BankCard {

    protected BigDecimal balance;
    protected BigDecimal amountSpent = new BigDecimal(0);
    public BankCard(BigDecimal balance) {
        this.balance = balance;
    }
    public void replenishment(BigDecimal amount){

        balance = balance.add(amount);
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public abstract BigDecimal getAvailableFunds();
    public abstract boolean pay(BigDecimal amount);

}
