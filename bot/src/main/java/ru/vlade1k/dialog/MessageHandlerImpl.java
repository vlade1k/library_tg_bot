package ru.vlade1k.dialog;

import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.vlade1k.data.IStorage;
import ru.vlade1k.executor.MessageExecutor;
import ru.vlade1k.data.Storage;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MessageHandlerImpl implements MessageHandler {
  private final Map<Long, State> usersStateMap;
  private final IStorage storage;
  private final TelegramClient client;

  public MessageHandlerImpl(TelegramClient client) {
    this.usersStateMap = new HashMap<>();
    this.storage = new Storage();
    this.client = client;
  }

  @Override
  public void handleMessage(Message message) throws TelegramApiException {
    Long userId = message.getChatId();

    if (!usersStateMap.containsKey(userId) ||
        usersStateMap.get(userId) == State.FREE
    ) {
      usersStateMap.put(userId, StateInitializer.getStateByMessage(message));
    }

    if (usersStateMap.containsKey(userId)) {
      State state = usersStateMap.get(userId);
      try {
        MessageExecutor executor = state.messageExecutorClass
                                        .getConstructor(IStorage.class, TelegramClient.class)
                                        .newInstance(storage, client);
        executor.executeMessage(message);
        usersStateMap.put(message.getChatId(), executor.getNextState());
      } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
