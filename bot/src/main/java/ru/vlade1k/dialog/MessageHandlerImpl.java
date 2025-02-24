package ru.vlade1k.dialog;

import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.vlade1k.data.Storage;

import java.util.HashMap;
import java.util.Map;

public class MessageHandlerImpl implements MessageHandler {
  private final Map<Long, State> usersStateMap;
  private final Storage storage;
  private final MessageExecutor executor;

  public MessageHandlerImpl(TelegramClient client) {
    this.usersStateMap = new HashMap<>();
    this.storage = new Storage();
    this.executor = new MessageExecutor(client, storage);
  }

  @Override
  public void handleMessage(Message message) throws TelegramApiException {
    Long userId = message.getChatId();
    String messageText = message.getText();

    if (!storage.hasUser(userId) &&
        !usersStateMap.containsKey(userId)
    ) {
      usersStateMap.put(userId, State.GREETING);
    }

    if (usersStateMap.containsKey(userId)) {
      State state = usersStateMap.get(userId);
      switch (state) {
        case GREETING:
          executor.greeting(userId);
          usersStateMap.put(userId, State.NAME_REGISTER);
          break;
        case NAME_REGISTER: {
          boolean result = executor.verifyUserPersonData(userId, messageText);
          usersStateMap.put(userId, result ? State.COURSE_REGISTER : State.NAME_REGISTER);
          break;
        }
        case COURSE_REGISTER: {
          boolean result = executor.verifyCourseNumber(userId, messageText);
          usersStateMap.put(userId, result ? State.SUCCESS_REGISTER : State.COURSE_REGISTER);
          break;
        }
        case SUCCESS_REGISTER:
          executor.pullFace(userId, messageText);
          break;
      }
    }
  }
}
