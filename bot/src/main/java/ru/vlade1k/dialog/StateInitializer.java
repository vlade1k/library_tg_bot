package ru.vlade1k.dialog;

import org.telegram.telegrambots.meta.api.objects.message.Message;

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

}
