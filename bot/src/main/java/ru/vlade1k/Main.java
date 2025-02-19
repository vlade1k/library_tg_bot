package ru.vlade1k;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import ru.vlade1k.exception.ReadConfigException;
import ru.vlade1k.util.ConfigData;
import ru.vlade1k.util.ConfigReader;

public class Main {
  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    try {
      TelegramBotsLongPollingApplication botApplication = new TelegramBotsLongPollingApplication();
      ConfigData data = ConfigReader.getTokenFromConfig();
      botApplication.registerBot(data.token(), new Bot(data.token()));
    } catch (ReadConfigException e) {
      log.error("Failed to read config", e);
    } catch (Exception e) {

    }
  }
}
