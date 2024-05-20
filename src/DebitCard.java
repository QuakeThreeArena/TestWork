import java.math.BigDecimal;

public class DebitCard extends BankCard{

    public DebitCard(BigDecimal balance) {
        super(balance);
    }
    @Override
    public void replenishment(BigDecimal amount) {
        super.replenishment(amount);
    }

    @Override
    public BigDecimal getAvailableFunds() {
        return super.getBalance();
    }

    @Override
    public boolean pay(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0){
            balance = balance.subtract(amount);
            amountSpent = amountSpent.add(amount);
            return true;
        }
        return false;
    }


}
