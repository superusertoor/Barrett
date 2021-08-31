package toor.barrettbot.environment.sniper.sniper.utils;

import toor.barrettbot.environment.sniper.openmcauthenticator.exceptions.InvalidCredentialsException;
import toor.barrettbot.environment.sniper.sniper.main.Account;
import toor.barrettbot.environment.sniper.sniper.sniper.Authentication;

public class Authenticator {

    public static Authentication authenticate(String email, String password) throws InvalidCredentialsException {
        return new Authentication(new Account().setEmail(email).setPassword(password));
    }

}
