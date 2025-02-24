package ru.vlade1k.data;

import java.util.Objects;

public class UserData {
  private final String name;
  private final String surname;
  private final String secondName;

  private int course;

  public UserData(String name, String surname, String secondName) {
    this.name = name;
    this.surname = surname;
    this.secondName = secondName;
  }

  public void setCourse(int course) {
    this.course = course;
  }

  public static UserData createUserFromMessage(String message) {
    String[] data = message.split(" ");
    return new UserData(data[0], data[1], data[2]);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof UserData userData)) {
      return false;
    }
    return course == userData.course &&
           Objects.equals(name, userData.name) &&
           Objects.equals(surname, userData.surname) &&
           Objects.equals(secondName, userData.secondName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, surname, secondName, course);
  }
}
