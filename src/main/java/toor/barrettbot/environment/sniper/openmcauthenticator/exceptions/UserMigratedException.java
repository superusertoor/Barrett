package toor.barrettbot.environment.sniper.openmcauthenticator.exceptions;

import toor.barrettbot.environment.sniper.openmcauthenticator.responses.ErrorResponse;

public class UserMigratedException extends RequestException {

  public UserMigratedException(ErrorResponse error) {
    super(error);
  }

}
