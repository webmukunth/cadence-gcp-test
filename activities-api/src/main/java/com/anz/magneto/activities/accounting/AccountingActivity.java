package com.anz.magneto.activities.accounting;

import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;
import lombok.NonNull;

public interface AccountingActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 5, taskList = Constants.TASK_LIST)
  @NonNull AccountingResponse reverseDebitCustomerCreditFloat(
      @NonNull WorkflowRequest request, @NonNull AccountingResponse originalResponse);

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 5, taskList = Constants.TASK_LIST)
  @NonNull AccountingResponse debitCustomerCreditFloat(@NonNull WorkflowRequest request);

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 5, taskList = Constants.TASK_LIST)
  @NonNull AccountingResponse forceDebitCustomerCreditFloat(@NonNull WorkflowRequest request);
}