package ru.vlade1k.data;

import ru.vlade1k.util.DataUtil;

import java.util.HashMap;
import java.util.Map;

public class Storage implements IStorage {
  private final Map<Long, UserData> registeredUsers = new HashMap<>();

  public boolean addUser(Long id, UserData user) {
    registeredUsers.put(id, user);
    return true;
  }

  @Override
  public boolean userIsExist(Long chatId) {
    return registeredUsers.containsKey(chatId);
  }

  @Override
  public boolean changeUserData(Long chatId, UserData userData) {
    UserData user = registeredUsers.get(chatId);
    if (!DataUtil.objectDataVerify(userData)) {
      return false;
    }

    user.changeUserData(userData);
    return true;
  }

  public UserData getUser(Long id) {
    return registeredUsers.get(id);
  }
}
