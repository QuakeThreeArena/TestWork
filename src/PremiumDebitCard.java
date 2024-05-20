import java.math.BigDecimal;
import java.text.DecimalFormat;

public class PremiumDebitCard extends DebitCard{
    public PremiumDebitCard(BigDecimal balance) {
        super(balance);
    }
    protected BigDecimal cashBackRatio = BigDecimal.valueOf(0.01); // Кэшбэк 1%
    private BigDecimal cashBackBalance = new BigDecimal(0);

    @Override
    public boolean pay(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0){
            balance = balance.subtract(amount);
            cashBackBalance = cashBackBalance.add(amount.multiply(cashBackRatio));
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal getAvailableFunds() {
        return super.getBalance().add(getCashBackBalance());
    }


    public BigDecimal getCashBackBalance (){
        DecimalFormat format = new DecimalFormat("0.##");
        return BigDecimal.valueOf(Long.parseLong(format.format(cashBackBalance)));
    }



}
