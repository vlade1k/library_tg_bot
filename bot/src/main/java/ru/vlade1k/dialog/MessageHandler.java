package ru.vlade1k.dialog;

import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface MessageHandler {
  void handleMessage(Message message) throws TelegramApiException;
}
