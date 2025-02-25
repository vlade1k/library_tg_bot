package ru.vlade1k.dialog;

import org.telegram.telegrambots.meta.api.objects.message.Message;
import ru.vlade1k.executor.CourseRegisterExecutor;
import ru.vlade1k.executor.MessageExecutor;
import ru.vlade1k.executor.PullFaceExecutor;
import ru.vlade1k.executor.RegisterNameExecutor;
import ru.vlade1k.executor.StartRegisterExecutor;
import ru.vlade1k.executor.UnexpectedMessageExecutor;

import java.util.Map;

public class StateInitializer {
  private static Map<String, State> messageState = Map.of(
      "/start", State.START_REGISTER,
      "/pull_face", State.PULL_FACE
  );

  public static State getStateByMessage(Message message) {
    if (    message.getText() == null
        ||  !messageState.containsKey(message.getText())) {
      return State.UNEXPECTED;
    }

    return messageState.get(message.getText());
  }

  public enum State {
    START_REGISTER(StartRegisterExecutor.class),
    NAME_REGISTER(RegisterNameExecutor.class),
    COURSE_REGISTER(CourseRegisterExecutor.class),
    PULL_FACE(PullFaceExecutor.class),
    UNEXPECTED(UnexpectedMessageExecutor.class),
    FREE(MessageExecutor.class);


    final Class<? extends MessageExecutor> messageExecutorClass;

    State(Class<? extends MessageExecutor> messageExecutorClass) {
      this.messageExecutorClass = messageExecutorClass;
    }
  }

}
