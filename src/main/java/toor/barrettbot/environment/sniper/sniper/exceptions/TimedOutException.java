package toor.barrettbot.environment.sniper.sniper.exceptions;

import toor.barrettbot.environment.sniper.sniper.main.Account;

public class TimedOutException extends Exception {

    private static final String message = "Die Anfrage konnte nicht verarbeitet werden.";

    public TimedOutException(Account account) {
        super(message);
    }
}
