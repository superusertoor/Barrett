package toor.barrettbot.environment.sniper.openmcauthenticator.exceptions;

import toor.barrettbot.environment.sniper.openmcauthenticator.responses.ErrorResponse;

public class InvalidTokenException extends RequestException {

  public InvalidTokenException(ErrorResponse error) {
    super(error);
  }

}
