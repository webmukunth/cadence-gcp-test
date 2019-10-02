package com.anz.cadence.activities.samplepayment.accounting;

import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;
import lombok.NonNull;

public interface AccountingActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 60, taskList = Constants.TASK_LIST)
  @NonNull AccountingResponse reverseDebitCustomerCreditFloat(
      @NonNull WorkflowRequest request, @NonNull AccountingResponse originalResponse);

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 60, taskList = Constants.TASK_LIST)
  @NonNull AccountingResponse debitCustomerCreditFloat(@NonNull WorkflowRequest request);

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 60, taskList = Constants.TASK_LIST)
  @NonNull AccountingResponse forceDebitCustomerCreditFloat(@NonNull WorkflowRequest request);
}
