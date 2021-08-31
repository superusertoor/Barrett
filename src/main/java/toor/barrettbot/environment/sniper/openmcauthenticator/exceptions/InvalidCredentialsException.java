package toor.barrettbot.environment.sniper.openmcauthenticator.exceptions;

import toor.barrettbot.environment.sniper.openmcauthenticator.responses.ErrorResponse;

public class InvalidCredentialsException extends RequestException {

    public InvalidCredentialsException(ErrorResponse error) {
       super(error);
    }
}
