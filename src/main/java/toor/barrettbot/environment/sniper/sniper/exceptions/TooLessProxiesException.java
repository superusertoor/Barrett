package toor.barrettbot.environment.sniper.sniper.exceptions;

import toor.barrettbot.environment.sniper.sniper.main.Account;

public class TooLessProxiesException extends Exception {

    private static final String message = "Es stehen nicht genügend Proxies für einen Snipe zur verfügung.";

    public TooLessProxiesException(Account account) {
        super(message);
    }

}
