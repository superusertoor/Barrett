package toor.barrettbot.environment.sniper.sniper.exceptions;

import toor.barrettbot.environment.sniper.sniper.main.Account;

public class TooManyRequestsException extends Exception {

    private static final String message = "Es wurden zu viele Anfragen geschickt";

    public TooManyRequestsException(Account account) {
        super(message);
    }
}
