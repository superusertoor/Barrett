package toor.barrettbot.environment.sniper.sniper.exceptions;

import toor.barrettbot.environment.sniper.sniper.main.Account;

public class InvalidNameException extends Exception {

    private static final String message = "Der gewünschte Name ist ungültig\"";

    public InvalidNameException(Account account) {
        super(message);
    }
}
