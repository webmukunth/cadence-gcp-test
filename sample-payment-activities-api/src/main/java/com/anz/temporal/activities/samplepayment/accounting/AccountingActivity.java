package com.anz.temporal.activities.samplepayment.accounting;

import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import lombok.NonNull;

@ActivityInterface
public interface AccountingActivity {

  @ActivityMethod
  @NonNull AccountingResponse reverseDebitCustomerCreditFloat(
      @NonNull WorkflowRequest request, @NonNull AccountingResponse originalResponse);

  @ActivityMethod
  @NonNull AccountingResponse debitCustomerCreditFloat(@NonNull WorkflowRequest request);

  @ActivityMethod
  @NonNull AccountingResponse forceDebitCustomerCreditFloat(@NonNull WorkflowRequest request);
}
