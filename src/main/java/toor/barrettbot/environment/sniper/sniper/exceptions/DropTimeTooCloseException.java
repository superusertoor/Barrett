package toor.barrettbot.environment.sniper.sniper.exceptions;

import toor.barrettbot.environment.sniper.sniper.main.Account;

public class DropTimeTooCloseException extends Exception {

    private final static String message = "Der Name droppt zu früh";

    public DropTimeTooCloseException(Account account) {
        super(message);
    }

}