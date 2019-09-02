package com.anz.magneto.commons;

import java.lang.management.ManagementFactory;


public class Constants {

  public static final String TASK_LIST = "magneto";
  public static final String DOMAIN = "magneto";
  public static final String INSTANCE_NAME = ManagementFactory.getRuntimeMXBean().getName();
  public final static String CACHE_NAME = "paymentRequest";
  public final static String PAYMENT_TOPIC = "magneto.payments";
}
