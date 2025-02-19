package ru.vlade1k.util;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class ConfigReader {

  public static ConfigData getTokenFromConfig() throws FileNotFoundException {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(Objects.requireNonNull(ConfigReader.class
        .getClassLoader()
        .getResource("config.json")).getPath())
    ) {
      return gson.fromJson(reader, ConfigData.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
