import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PremiumDebitCardTest {

    @Test
    void whenEnoughMoneyToPay() {
        PremiumDebitCard card = new PremiumDebitCard(BigDecimal.valueOf(15000));
        //Карта с балансом 15000
        card.pay(BigDecimal.valueOf(9000));
        //Соверашем оплату на 9000
        assertEquals(BigDecimal.valueOf(6000),card.getBalance());
        //Проверяет что у нас списали деженые средства
    }
    @Test
    void whenWithoutMoneyToPay() {
        PremiumDebitCard card = new PremiumDebitCard(BigDecimal.valueOf(8000));
        //Карта с балансом 8000
        card.pay(BigDecimal.valueOf(9000));
        //Пытаемся совершить оплату на 9000
        assertEquals(BigDecimal.valueOf(8000),card.getBalance());
        //Проверяет, что оплата не прошла, так как у нас недостаточно средст для совершения операции
    }

    @Test
    void getCashBackBalance() {
        PremiumDebitCard card = new PremiumDebitCard(BigDecimal.valueOf(15000));
        //Карта с балансом 15000
        assertEquals(BigDecimal.valueOf(0),card.getCashBackBalance());
        //Првоеряем что у нас кешбэк равен 0, так как мы не совершали оплату
        card.pay(BigDecimal.valueOf(9000));
        //Мы совершили покупку, чтобы у нас был кэшбек
        assertEquals(BigDecimal.valueOf(90),card.getCashBackBalance());
        //После соершения покупки в размере 9000, нам должен начислиться кешбэк в размере 1% от оплаты, в данном случае 90
    }
}