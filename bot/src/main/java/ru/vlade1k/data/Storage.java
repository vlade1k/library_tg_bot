package ru.vlade1k.data;

import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
  private final Map<Long, UserData> registeredUsers = new HashMap<>();

  public boolean hasUser(Long id) {
    return registeredUsers.containsKey(id);
  }

  public void addUser(Long id, UserData user) {
    registeredUsers.put(id, user);
  }

  public UserData getUser(Long id) {
    return registeredUsers.get(id);
  }
}
