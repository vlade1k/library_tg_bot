package ru.vlade1k;

import com.google.gson.internal.bind.util.ISO8601Utils;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class Bot implements LongPollingSingleThreadUpdateConsumer {
  private final TelegramClient client;

  public Bot(String token) {
    client = new OkHttpTelegramClient(token);
  }

  @Override
  public void consume(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      System.out.println(update.getMessage().getText());
      SendMessage sendMessage = new SendMessage(Long.toString(update.getMessage().getChatId()),
                                                              update.getMessage().getText());
      try {
        client.execute(sendMessage);
      } catch (TelegramApiException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
