package ru.vlade1k.executor;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.vlade1k.data.IStorage;
import ru.vlade1k.dialog.State;

public class UnexpectedMessageExecutor extends MessageExecutor {

  public UnexpectedMessageExecutor(IStorage storage, TelegramClient client) {
    super(storage, client);
  }

  @Override
  public boolean executeMessage(Message message) throws TelegramApiException {
    client.execute(new SendMessage(Long.toString(message.getChatId()), "Извините, я не знаю данной команды!\nПопробуйте ещё раз!"));
    this.nextState = State.FREE;
    return true;
  }
}
