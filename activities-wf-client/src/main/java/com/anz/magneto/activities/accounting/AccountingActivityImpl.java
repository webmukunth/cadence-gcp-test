package com.anz.magneto.activities.accounting;

import static com.anz.magneto.activities.accounting.AccountingStatus.SUCCESS;

import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountingActivityImpl implements AccountingActivity {

  @Override
  public @NonNull AccountingResponse reverseDebitCustomerCreditFloat(
      @NonNull WorkflowRequest request, @NonNull AccountingResponse originalResponse) {

    /* Unique id generated based on string, this can prevent accidental duplicate request to DS */
    var reverseAccountingId = UUID.nameUUIDFromBytes(
        (originalResponse.getAccountingId() + "-reverse").getBytes(StandardCharsets.UTF_8)
    ).toString();
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
    var accountingId = UUID.nameUUIDFromBytes(
        (request.getRequestId() + "-debitCustomerCreditFloat").getBytes(StandardCharsets.UTF_8)
    ).toString();

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
    var accountingId = UUID.nameUUIDFromBytes(
        (request.getRequestId() + "-forceDebitCustomerCreditFloat").getBytes(StandardCharsets.UTF_8)
    ).toString();
    log.info("forceDebitCustomerCreditFloat: accountingId={} request={}", accountingId, request);
    return AccountingResponse.builder()
        .status(SUCCESS)
        .accountingId(accountingId)
        .build();
  }
}
