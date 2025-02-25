package ru.vlade1k.data;

public interface IStorage {
  UserData getUser(Long chatId);
  boolean addUser(Long chatId, UserData userData);
  boolean userIsExist(Long chatId);
  boolean changeUserData(Long chatId, UserData userData);
}
