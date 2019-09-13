package com.anz.magneto.commons.api.workflow;

public class StopWorkflowException extends Exception {

  private static final long serialVersionUID = 5452168407645392211L;

  public StopWorkflowException(String message) {
    super(message);
  }
}
