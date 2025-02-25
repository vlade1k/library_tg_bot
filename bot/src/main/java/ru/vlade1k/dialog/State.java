package ru.vlade1k.dialog;

import ru.vlade1k.executor.CourseRegisterExecutor;
import ru.vlade1k.executor.MessageExecutor;
import ru.vlade1k.executor.PullFaceExecutor;
import ru.vlade1k.executor.RegisterNameExecutor;
import ru.vlade1k.executor.StartRegisterExecutor;
import ru.vlade1k.executor.UnexpectedMessageExecutor;

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
