package toor.barrettbot.environment.sniper.openmcauthenticator.responses;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

  private String error;
  private String errorMessage;
  private String cause;

  public String getError() {
    return error;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public String getCause() {
    return cause;
  }

}
