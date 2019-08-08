package com.anz.magneto.api.download;

import com.anz.magneto.commons.Constants;
import com.uber.cadence.activity.ActivityMethod;
import lombok.Getter;
import lombok.ToString;

public interface DownloadActivity {

  @ActivityMethod(
      scheduleToCloseTimeoutSeconds = 301,
      taskList = Constants.TASK_LIST
  )
  TaskListFileNamePair download(String fileName);

  @ToString
  @Getter
  final class TaskListFileNamePair {

    private final String hostTaskList;
    private final String filePath;

    public TaskListFileNamePair(String hostTaskList, String filePath) {
      this.hostTaskList = hostTaskList;
      this.filePath = filePath;
    }
  }
}
