package telegrambot.controller.telegram.bot.notification;

import telegrambot.controller.telegram.bot.CurrencyTelegramBot;
import telegrambot.controller.user.UserSettings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Thread.sleep;

public class NotifierStream implements Runnable{
    UserSettings userSettings = new UserSettings();
    CurrencyTelegramBot currency = new CurrencyTelegramBot();

    public void idler(UserSettings userSettings) {
        while (true) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String notificationTime = userSettings.getNotificationTime() + ":00:00";
            if (notificationTime.equals(dtf.format(now))) {
                currency.notifier();
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
        idler(userSettings);
    }
}
