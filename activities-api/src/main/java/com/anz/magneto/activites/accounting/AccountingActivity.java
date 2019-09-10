package com.anz.magneto.activites.accounting;

import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;

public interface AccountingActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 5, taskList = Constants.TASK_LIST)
  AccountingResponse debitCustomerCreditFloat(WorkflowRequest request);
}
