package com.anz.magneto.api.download;

import com.anz.magneto.commons.Constants;
import com.uber.cadence.activity.ActivityMethod;

public interface ProcessFileActivity {

  @ActivityMethod(
      scheduleToCloseTimeoutSeconds = 302,
      taskList = Constants.TASK_LIST
  )
  void process(String filePath);
}
