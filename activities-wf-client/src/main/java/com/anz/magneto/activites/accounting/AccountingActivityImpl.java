package com.anz.magneto.activites.accounting;

import com.anz.magneto.commons.api.Status;
import com.anz.magneto.commons.api.WorkflowRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountingActivityImpl implements AccountingActivity {

  @Override
  public AccountingResponse debitCustomerCreditFloat(WorkflowRequest request) {
    log.info("debitCustomerCreditFloat: {}", request);
    return AccountingResponse.builder().status(Status.SUCCESS).build();
  }
}
