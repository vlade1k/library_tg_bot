package ru.vlade1k.executor;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.vlade1k.data.IStorage;
import ru.vlade1k.data.UserData;
import ru.vlade1k.dialog.StateInitializer.State;
import ru.vlade1k.exception.NoUserFoundException;
import ru.vlade1k.util.DataUtil;

public class CourseRegisterExecutor extends MessageExecutor {

  public CourseRegisterExecutor(IStorage storage, TelegramClient client) {
    super(storage, client);
  }

  @Override
  public boolean executeMessage(Message message) throws TelegramApiException {
    if (DataUtil.courseVerify(message.getText())) {
      if (!storage.userIsExist(message.getChatId())) {
        throw new NoUserFoundException();
      }

      UserData user = storage.getUser(message.getChatId());
      user.setCourse(Integer.parseInt(message.getText()));

      this.nextState = State.FREE;
      this.client.execute(new SendMessage(Long.toString(message.getChatId()), "Номер курса добавлен успешно!\n"
          + "Поздравляю, вы зарегистрировались успешно!\n"));

      return true;
    }

    this.nextState = State.COURSE_REGISTER;
    this.client.execute(new SendMessage(Long.toString(message.getChatId()), "Ошибка при вводе номера курса!\n"
        + "Пожалуйста, попробуйте ещё раз!"));

    return false;
  }
}
