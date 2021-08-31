package toor.barrettbot.environment.sniper.sniper.exceptions;

import toor.barrettbot.environment.sniper.sniper.main.Account;

public class UnavailableNameException extends Exception {

    private static final String message = "Der gewünschte Name ist nicht verfügbar";

    public UnavailableNameException(Account account) {
        super(message);
    }
}