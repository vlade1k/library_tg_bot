package ru.vlade1k;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import ru.vlade1k.exception.EmptyMessageException;
import ru.vlade1k.exception.ReadConfigException;
import ru.vlade1k.util.ConfigData;
import ru.vlade1k.util.ConfigReader;

public class Main {
  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    try {
      TelegramBotsLongPollingApplication botApplication = new TelegramBotsLongPollingApplication();

      ConfigData data = ConfigReader.getTokenFromConfig();
      String token = data.token();
      botApplication.registerBot(token, new Bot(token));
    } catch (ReadConfigException e) {
      log.error("Failed to read config.", e);
    } catch (EmptyMessageException e) {
      log.error("Failed to reply to the message.");
    } catch (Exception e) {
      log.error("Fatal Error.");
    }
  }
}
