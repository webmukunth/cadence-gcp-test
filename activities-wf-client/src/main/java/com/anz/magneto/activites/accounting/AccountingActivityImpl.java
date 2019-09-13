package com.anz.magneto.activites.accounting;

import static com.anz.magneto.activites.accounting.AccountingStatus.SUCCESS;

import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountingActivityImpl implements AccountingActivity {

  @Override
  public @NonNull AccountingResponse reverseDebitCustomerCreditFloat(
      @NonNull WorkflowRequest request, @NonNull AccountingResponse originalResponse) {

    /* Unique id generated based on string, this can prevent accidental duplicate request to DS */
    var reverseAccountingId =
        UUID.fromString(originalResponse.getAccountingId() + "-reverse").toString();
    log.info(
        "reverseDebitCustomerCreditFloat: originalResponse={} reverseAccountingId={} request={}",
        originalResponse, reverseAccountingId, request);
    return AccountingResponse.builder()
        .status(SUCCESS)
        .accountingId(reverseAccountingId)
        .build();
  }

  @Override
  public @NonNull AccountingResponse debitCustomerCreditFloat(@NonNull WorkflowRequest request) {
    /* Unique id generated based on string, this can prevent accidental duplicate request to DS */
    var accountingId = UUID.fromString(request.getRequestId() + "-debitCustomerCreditFloat")
        .toString();
    log.info("debitCustomerCreditFloat: accountingId={} request={}", accountingId, request);
    return AccountingResponse.builder()
        .status(SUCCESS)
        .accountingId(accountingId)
        .build();
  }

  @Override
  public @NonNull AccountingResponse forceDebitCustomerCreditFloat(
      @NonNull WorkflowRequest request) {
    /* Unique id generated based on string, this can prevent accidental duplicate request to DS */
    var accountingId = UUID.fromString(request.getRequestId() + "-forceDebitCustomerCreditFloat")
        .toString();
    log.info("forceDebitCustomerCreditFloat: accountingId={} request={}", accountingId, request);
    return AccountingResponse.builder()
        .status(SUCCESS)
        .accountingId(accountingId)
        .build();
  }
}
