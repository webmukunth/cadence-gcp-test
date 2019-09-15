package com.anz.magneto.samplepayment;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.testing.TestWorkflowEnvironment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainApplicationTest {

  private TestWorkflowEnvironment testEnv;
  private WorkflowClient workflowClient;

  @BeforeEach
  void setUp() {
    testEnv = TestWorkflowEnvironment.newInstance();
    workflowClient = testEnv.newWorkflowClient();
    testEnv.start();
  }

  @AfterEach
  void tearDown() {
    testEnv.close();
  }

  @Test
  void main() {
  }
}