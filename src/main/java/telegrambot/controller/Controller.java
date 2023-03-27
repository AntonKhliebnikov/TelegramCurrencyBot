package telegrambot.controller;

import telegrambot.controller.currency.client.model.Currency;
import telegrambot.controller.currency.client.util.ConvertRate;
import telegrambot.controller.user.UserSettings;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public class Controller {
    private static final String PRIVATE = "Privat";
    private static final String MONO = "Mono";
    private static final String NBU = "NBU";
    UserSettings settings = new UserSettings();
    String[] currencyName = settings.getCurrency();


    public String resultMessage() throws IOException, URISyntaxException, InterruptedException {
        StringBuilder response = new StringBuilder("Exchange rate in " + changeNameBank());
        for (Currency dataBankRate : ConvertRate.allCurrencyToList()) {
            if (settings.getBank().equals(dataBankRate.getBankName())) {
                if (dataBankRate.getCurrencyName().equals(currencyName[0])) {
                    response
                            .append("\nUSD/UAH\n" + "Purchase: ")
                            .append(roundedRate(dataBankRate.getBuy()))
                            .append("\nSelling: ")
                            .append(roundedRate(dataBankRate.getSell()));
                }
                if (dataBankRate.getCurrencyName().equals(currencyName[1])) {
                    response
                            .append("\nEUR/UAH\n" + "Purchase: ")
                            .append(roundedRate(dataBankRate.getBuy()))
                            .append("\nSelling: ")
                            .append(roundedRate(dataBankRate.getSell()));
                }
            }
            if (currencyName[0].equals(currencyName[1])) {
                response = new StringBuilder("Please select a currency");
            }
        }
        return response.toString();
    }

    private String changeNameBank() {
        String resultNameBank = null;
        switch (settings.getBank()) {
            case PRIVATE:
                resultNameBank = "PrivatBank";
                break;
            case MONO:
                resultNameBank = "MonoBank";
                break;
            case NBU:
                resultNameBank = "NBU";
                break;
        }
        return resultNameBank;
    }

    private String roundedRate(BigDecimal rate) {
        String roundedRate;
        if (!(rate == null)) {
            roundedRate = String.format("%." + settings.getPrecession() + "f", rate);
        } else {
            roundedRate = "This operation is not performed";
        }
        return roundedRate;
    }
}
