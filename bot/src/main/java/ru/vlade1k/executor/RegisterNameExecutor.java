package ru.vlade1k.executor;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.vlade1k.data.IStorage;
import ru.vlade1k.data.UserData;
import ru.vlade1k.dialog.StateInitializer.State;
import ru.vlade1k.util.DataUtil;

public class RegisterNameExecutor extends MessageExecutor {

  public RegisterNameExecutor(IStorage storage, TelegramClient client) {
    super(storage, client);
  }

  @Override
  public boolean executeMessage(Message message) throws TelegramApiException {
    String stringMessage = message.getText();
    Long id = message.getChatId();

    if (DataUtil.nameVerify(stringMessage)) {
      storage.addUser(id, UserData.createUserFromMessage(stringMessage));
      client.execute(new SendMessage(Long.toString(id), "Вы успешно ввели своё имя!\n"));
      client.execute(new SendMessage(Long.toString(id), "Далее, мне нужен будет номер курса(только цифра):"));

      nextState = State.COURSE_REGISTER;
      return true;
    }

    client.execute(new SendMessage(Long.toString(id), "При регистрации была допущена ошибка!\nВведите ФИО ещё раз:"));
    nextState = State.NAME_REGISTER;
    return false;
  }
}
