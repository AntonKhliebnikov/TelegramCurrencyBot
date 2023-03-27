package telegrambot.controller.currency.client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import telegrambot.controller.currency.client.BankClient;
import telegrambot.controller.currency.client.model.Currency;
import telegrambot.controller.user.UserSettings;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ConvertRate {
    protected static JsonArray responseToJsonArray(String url) throws IOException, InterruptedException, URISyntaxException {
        BankClient bankClient = new BankClient();
        String responseString = bankClient.getRate(url);
        return JsonParser.parseString(responseString).getAsJsonArray();
    }

    public static List<Currency> allCurrencyToList() throws InterruptedException, IOException, URISyntaxException {
        List<Currency> listCurrencies = new ArrayList<>();
        UserSettings settings = new UserSettings();
        switch (settings.getBank()) {
            case "Privat":
                PrivateBankConvertRate.convertResponseToCurrency(listCurrencies);
                break;
            case "Mono":
                new MonoBankConvertRate().convertResponseToCurrency(listCurrencies);
                break;
            case "NBU":
                new NationalBankConvertRate().convertResponseToCurrency(listCurrencies);
                break;
        }
        return listCurrencies;
    }
}
