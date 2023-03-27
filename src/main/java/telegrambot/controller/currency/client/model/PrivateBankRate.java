package telegrambot.controller.currency.client.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class PrivateBankRate {
    @Getter
    @Setter
    private String currency;
    @Setter
    private String baseCurrency;
    @Getter
    @Setter
    private BigDecimal buy;
    @Getter
    @Setter
    private BigDecimal sell;
}
