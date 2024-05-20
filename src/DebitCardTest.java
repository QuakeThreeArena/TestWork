import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DebitCardTest {

    @Test
    void replenishment() {
        DebitCard card = new DebitCard(BigDecimal.valueOf(0));
        //Карта с 0 балансом
        card.replenishment(BigDecimal.valueOf(10000));
        //Пополняем карту на 10000
        assertEquals(BigDecimal.valueOf(10000),card.balance);
        //Проверяем баланс карты, что наша карта пополнилась
    }

    @Test
    void getAvailableFunds() {
        DebitCard card = new DebitCard(BigDecimal.valueOf(4000));
        //Карта с балансом 4000
        assertEquals(BigDecimal.valueOf(4000),card.getAvailableFunds());
        //Проверяем что у нас есть 4000 доступных средств
    }

    @Test
    void whenWithoutMoneyToPay() {
        DebitCard card = new DebitCard(BigDecimal.valueOf(1000));
        //Карта с 1000 балансом
        card.pay(BigDecimal.valueOf(5000));
        //Пытаемся совершить покупку, когда у нас отсутствуют средства
        assertEquals(BigDecimal.valueOf(1000),card.getBalance());
        //Мы не смогли совершить покупку в 5000,так как у нас нехватает средств для совершения операции
    }

    @Test
    void whenEnoughMoneyToPay() {
        DebitCard card = new DebitCard(BigDecimal.valueOf(10000));
        //Карта с 10000 балансом
        card.pay(BigDecimal.valueOf(5000));
        //Пытаемся совершить покупку
        assertEquals(BigDecimal.valueOf(5000),card.getBalance());
        //Мы смогли совершить покупку в сразмере 5000, так как у нас на карте было достаточно средств для совершения операции
    }
}