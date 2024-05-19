package com.functionapp;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class App {

  @FunctionName("greeting")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = HttpMethod.GET, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> requestMessage,
      final ExecutionContext context) {

    String greeting = requestMessage.getQueryParameters().get("greeting");
    String name = requestMessage.getQueryParameters().get("name");

    if (greeting == null || name == null) {
      return requestMessage.createResponseBuilder(HttpStatus.BAD_REQUEST).build();
    } else {
      return requestMessage.createResponseBuilder(HttpStatus.OK).body(greeting + "! " + name + ".")
          .build();
    }
  }

}
