package ru.vlade1k.util;

import java.util.Arrays;

public class DataUtil {
  public static boolean nameVerify(String message) {
    String[] data = message.split(" ");
    return data.length == 3 && Arrays.stream(data).allMatch(s -> s.matches("^[A-Za-zА-Яа-яЁё]+$"));
  }

  public static boolean courseVerify(String message) {
    return message.matches("^[0-7]$");
  }
}
