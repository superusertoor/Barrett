package toor.barrettbot.environment.sniper.sniper.exceptions;

import toor.barrettbot.environment.sniper.sniper.main.Account;

public class InvalidCredentialsException extends Exception {

    private final static String message = "Die angegebenen Anmeldedaten sind ungültig";

    public InvalidCredentialsException(Account account) {
        super(message);
    }

}