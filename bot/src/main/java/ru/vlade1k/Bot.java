package ru.vlade1k;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.vlade1k.dialog.MessageHandler;
import ru.vlade1k.dialog.MessageHandlerImpl;
import ru.vlade1k.exception.EmptyMessageException;

public class Bot implements LongPollingSingleThreadUpdateConsumer {
  private final TelegramClient client;
  private MessageHandler messageHandler;

  public Bot(String token) {
    this.client = new OkHttpTelegramClient(token);
    this.messageHandler = new MessageHandlerImpl(client);
  }

  @Override
  public void consume(Update update) {
    if (update.hasMessage()) {
      if (!update.getMessage().hasText()) {
        throw new EmptyMessageException();
      }

      try {
        messageHandler.handleMessage(update.getMessage());
      } catch (TelegramApiException e) {
        throw new EmptyMessageException();
      }
    }
  }
}
