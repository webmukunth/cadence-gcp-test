package com.anz.cadence.commons;

import java.lang.management.ManagementFactory;
import org.springframework.http.MediaType;


public class Constants {

  public static final String TASK_LIST = "cadence";
  public static final String TASK_LIST_SAMPLE_PAYMENT = "cadence.samplepayment";
  public static final String TASK_LIST_ACCOUNTING = "cadence.accounting";
  public static final String TASK_LIST_CLEARING = "cadence.clearing";
  public static final String TASK_LIST_CLIENT_RESPONSE = "cadence.clientresponse";
  public static final String TASK_LIST_ENRICH = "cadence.enrich";
  public static final String TASK_LIST_FRAUD_CHECK = "cadence.fraud";
  public static final String TASK_LIST_LIMIT_CHECK = "cadence.limitcheck";
  public static final String TASK_LIST_VALIDATE = "cadence.validate";

  public static final String DOMAIN = "cadence";
  public static final String INSTANCE_NAME = ManagementFactory.getRuntimeMXBean().getName();
  public final static String CACHE_NAME = "paymentRequest";
  public final static String PAYMENT_TOPIC = "cadence.payments";


  public final static MediaType APPLICATION_VND_WF_RES_V1_JSON =
      new MediaType("application", "vnd.wf-res.v1+json");
  public final static String APPLICATION_VND_WF_RES_V_1_JSON_VALUE = "application/vnd.wf-res.v1+json";

  public final static MediaType APPLICATION_VND_GPA_V1_XML =
      new MediaType("application", "vnd.gpa.v1+xml");
  public final static String APPLICATION_VND_GPA_V1_XML_VALUE = "application/vnd.gpa.v1+xml";

  public final static MediaType APPLICATION_VND_GPA_V1_JSON =
      new MediaType("application", "vnd.gpa.v1+json");
  public final static String APPLICATION_VND_GPA_V1_JSON_VALUE = "application/vnd.gpa.v1+json";
}
