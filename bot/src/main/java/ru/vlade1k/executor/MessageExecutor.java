package ru.vlade1k.executor;

import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.vlade1k.data.IStorage;
import ru.vlade1k.dialog.State;

public abstract class MessageExecutor {
  protected State nextState;
  protected IStorage storage;
  protected TelegramClient client;

  public MessageExecutor(IStorage storage, TelegramClient client) {
    this.storage = storage;
    this.client = client;
  }

  public abstract boolean executeMessage(Message message) throws TelegramApiException;

  public State getNextState() {
    return this.nextState;
  }
}
