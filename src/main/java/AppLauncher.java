import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegrambot.controller.telegram.bot.CurrencyTelegramBot;
import telegrambot.controller.telegram.bot.notification.NotifierStream;

public class AppLauncher {
    public static void main(String[] args) {
        NotifierStream stream = new NotifierStream();
        TelegramBotsApi botsApi;
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new CurrencyTelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        stream.run();
    }
}
