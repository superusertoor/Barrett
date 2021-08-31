package toor.barrettbot.environment.sniper.sniper.sniper;

import toor.barrettbot.environment.sniper.openmcauthenticator.exceptions.InvalidCredentialsException;
import toor.barrettbot.environment.sniper.sniper.main.Account;

public class Authentication {

    private String accessToken;
    private Account account;

    public Authentication(Account account) throws InvalidCredentialsException {
        this.account = account;
        this.accessToken = "";
    }

    public Account getAccount() {
        return account;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
