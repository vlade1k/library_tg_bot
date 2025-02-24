package ru.vlade1k.dialog;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.vlade1k.data.Storage;
import ru.vlade1k.data.UserData;
import ru.vlade1k.exception.NoUserFoundException;
import ru.vlade1k.util.DataUtil;

public class MessageExecutor {
  private TelegramClient client;
  private Storage storage;

  public MessageExecutor(TelegramClient client, Storage storage) {
    this.client = client;
    this.storage = storage;
  }

  protected void greeting(Long id) throws TelegramApiException {
    client.execute(new SendMessage(Long.toString(id), "Здравствуйте! Я - бот-библиотекарь корейского клуба!\n"
                                                    + "Буду рад с вами познакомиться!\n"));
    client.execute(new SendMessage(Long.toString(id), "Сначала мне нужно будет ваше ФИО:"));
  }

  protected boolean verifyUserPersonData(Long id, String message) throws TelegramApiException {
    if (DataUtil.nameVerify(message)) {
      storage.addUser(id, UserData.createUserFromMessage(message));
      client.execute(new SendMessage(Long.toString(id), "Вы успешно ввели своё имя!\n"));
      client.execute(new SendMessage(Long.toString(id), "Далее, мне нужен будет номер курса(только цифра):"));
      return true;
    }

    client.execute(new SendMessage(Long.toString(id), "При регистрации была допущена ошибка!"));
    return false;
  }

  protected boolean verifyCourseNumber(Long id, String message) throws TelegramApiException {
    if (DataUtil.courseVerify(message)) {
      if (!storage.hasUser(id)) {
        throw new NoUserFoundException();
      }

      UserData user = storage.getUser(id);
      user.setCourse(Integer.parseInt(message));
      client.execute(new SendMessage(Long.toString(id), "Номер курса добавлен успешно!\n"
                                                      + "Поздравляю, вы зарегистрировались успешно!\n"
                                                      + "Теперь бот будет вас передразнивать!"));
      return true;
    }

    client.execute(new SendMessage(Long.toString(id), "Ошибка при вводе номера курса!\n"
                                                    + "Пожалуйста, попробуйте ещё раз!"));
    return false;
  }
  

  protected void pullFace(Long id, String message) throws TelegramApiException {
    client.execute(new SendMessage(Long.toString(id), message));
  }
}
