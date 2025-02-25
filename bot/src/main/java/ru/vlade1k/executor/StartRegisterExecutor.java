package ru.vlade1k.executor;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.vlade1k.data.IStorage;
import ru.vlade1k.dialog.State;

public class StartRegisterExecutor extends MessageExecutor {

  public StartRegisterExecutor(IStorage storage, TelegramClient client) {
    super(storage, client);
  }

  @Override
  public boolean executeMessage(Message message) throws TelegramApiException {
    client.execute(new SendMessage(Long.toString(message.getChatId()), "Здравствуйте! Я - бот-библиотекарь корейского клуба!\n"
        + "Буду рад с вами познакомиться!\n"));
    client.execute(new SendMessage(Long.toString(message.getChatId()), "Сначала мне нужно будет ваше ФИО:"));
    this.nextState = State.NAME_REGISTER;
    return true;
  }
}
