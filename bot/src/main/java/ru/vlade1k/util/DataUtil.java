package ru.vlade1k.util;

import ru.vlade1k.data.UserData;

import java.util.Arrays;

public class DataUtil {
  public static boolean nameVerify(String message) {
    String[] data = message.split(" ");
    return data.length == 3 && Arrays.stream(data).allMatch(s -> s.matches("^[A-Za-zА-Яа-яЁё]+$"));
  }

  public static boolean objectDataVerify(UserData userData) {
    String combinedName = userData.getName() + " " + userData.getSurname() + " " + userData.getSecondName();
    return nameVerify(combinedName) && courseVerify(Integer.toString(userData.getCourse()));
  }

  public static boolean courseVerify(String message) {
    return message.matches("^[0-9]$");
  }
}
