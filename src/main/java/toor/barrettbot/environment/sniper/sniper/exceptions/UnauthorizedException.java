package toor.barrettbot.environment.sniper.sniper.exceptions;

import toor.barrettbot.environment.sniper.sniper.main.Account;

public class UnauthorizedException extends Exception {

    private final static String message = "Der Account konnte nicht authorisiert werden.";

    public UnauthorizedException(Account account) {
        super(message);
    }
}