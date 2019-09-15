package com.anz.magneto.commons;

import java.lang.management.ManagementFactory;
import org.springframework.http.MediaType;


public class Constants {

  public static final String TASK_LIST = "magneto";
  public static final String DOMAIN = "magneto";
  public static final String INSTANCE_NAME = ManagementFactory.getRuntimeMXBean().getName();
  public final static String CACHE_NAME = "paymentRequest";
  public final static String PAYMENT_TOPIC = "magneto.payments";


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
