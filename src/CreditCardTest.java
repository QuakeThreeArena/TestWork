import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    @Test
    void creditCardWithDeptReplenishment() {
        CreditCard card = new CreditCard(BigDecimal.valueOf(0), BigDecimal.valueOf(10000), BigDecimal.valueOf(5000));
        //На карте имеются только кредитные 5000, с учетом того что кредитный лимит 10000
        card.replenishment(BigDecimal.valueOf(5000));
        //Мы кладем на карту 5000
        assertEquals(BigDecimal.valueOf(10000), card.getAvailableFunds());
        assertEquals(BigDecimal.valueOf(0), card.getBalance());
        //Мы сравниваем, что у нас закрылся кредит и доступных ссредств у нас нету

    }

    @Test
    void creditCardWithoutDeptReplenishment() {
        CreditCard card = new CreditCard(BigDecimal.valueOf(0), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000));
        //На карте имеются только кредитные 10000, но отсуствуют личныве средства
        card.replenishment(BigDecimal.valueOf(5000));
        //Мы кладем на карту 5000
        assertEquals(BigDecimal.valueOf(15000), card.getAvailableFunds());
        assertEquals(BigDecimal.valueOf(5000), card.getBalance());
        //Мы сравниваем, что у нас на карте пополнился баланс на 5000 личных средств и стало всех доступных средств на 5000 больше

    }

    @Test
    void creditCardWithDeptGetAvailableFunds() {
        CreditCard card = new CreditCard(BigDecimal.valueOf(0), BigDecimal.valueOf(10000), BigDecimal.valueOf(5000));
        //На карте отсутсвтуют собственные средства и имеется задолженность по кредитной карте в суммой 5000
        assertEquals(BigDecimal.valueOf(5000), card.getAvailableFunds());


    }

    @Test
    void creditCardWithoutGetAvailableFunds() {
        CreditCard card = new CreditCard(BigDecimal.valueOf(5000), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000));
        //На карте отсутсвтует задолженность и имеются кредитный лемит в размере 10000 и собственные средства в размере 5000
        assertEquals(BigDecimal.valueOf(15000), card.getAvailableFunds());

    }

    @Test
    void threeTestGetAvailableFunds() {
        CreditCard card = new CreditCard(BigDecimal.valueOf(0), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000));
        // На карте отстсвуют личне деньги,но присутствует кредитный лемит в размере 10000
        assertEquals(BigDecimal.valueOf(10000), card.getAvailableFunds());


    }


    @Test
    void whenEnoughMoneyToPay() {
        CreditCard card = new CreditCard(BigDecimal.valueOf(20000), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000));
        //На карте имеются 30000 средств, включая собственных 10000 и кредитных 10000
        assertEquals(true,card.pay(BigDecimal.valueOf(15000)));
        //Совершаем покупку на 15000
        assertEquals(BigDecimal.valueOf(15000), card.getAvailableFunds());
        assertEquals(BigDecimal.valueOf(5000), card.getBalance());
        //ПРоверяем что у нас бааланс собственных средст после покупки стал 5000 и мы взяли кредит 10000
    }
    @Test
    void whenNotEnoughMoneyToPay() {
        CreditCard card = new CreditCard(BigDecimal.valueOf(10000), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000));
        //На карте имеются 20000 средств, включая собственных 10000 и кредитных 10000
        assertEquals(false,card.pay(BigDecimal.valueOf(50000)));
        //Мы пытаемя осуществить покупку на 50000
          assertEquals(BigDecimal.valueOf(20000), card.getAvailableFunds());
          assertEquals(BigDecimal.valueOf(10000), card.getBalance());
          // Мы не смогли осуществить покупку на 50000, следовательно наш баланс не уменьшился, т.к. нету досточно количества средств

    }

}