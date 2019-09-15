package com.anz.magneto.commons.api;

public enum EventType {
  RECEIVED,
  CUSTOMER_DEBITTED,
  CUSTOMER_DEBIT_REVERSED, CUSTOMER_FORCE_DEBITTED, PAYMENT_CLEARED, CLIENT_RESPONSE, ENRICHED, FRAUD_CHECK_PASS, LIMIT_CHECK_PASS, VALIDATED, ACCEPTED
}
