package toor.barrettbot.environment.sniper.sniper.main;

import toor.barrettbot.environment.sniper.sniper.exceptions.TimedOutException;
import toor.barrettbot.environment.sniper.sniper.exceptions.TooManyRequestsException;
import toor.barrettbot.environment.sniper.sniper.exceptions.UnauthorizedException;
import toor.barrettbot.environment.sniper.sniper.responses.NameSnipedResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SnipeRequest extends Thread {

    private Account account;
    private String token;

    public SnipeRequest(Account account, String token) {
        this.account = account;
        this.token = token;
    }

    @Override
    public void start() {
        snipe();
    }

    private void snipe() {
        try {
            URL url = new URL("https://api.minecraftservices.com/minecraft/profile/name/" + account.getWantedName());
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:68.0) Gecko/20100101 Firefox/68.0");
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("Accept-Encoding", "gzip, deflate");
            con.setRequestProperty("Authorization", "Bearer " + token);
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setConnectTimeout(6000);
            con.setReadTimeout(6000);
            con.setDoOutput(true);
            String jsonInputString = "{\"name\":\"" + account.getWantedName() + "\",\"password\":\"" + account.getPassword() + "\"}";
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            if (con.getResponseCode() == 401) {
                throw new UnauthorizedException(account);
            } else if (con.getResponseCode() == 429) {
                throw new TooManyRequestsException(account);
            } else if (con.getResponseCode() == 500) {
                throw new TimedOutException(account);
            } else if (con.getResponseCode() == 200) {
                throw new NameSnipedResponse(account);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        } catch (TimedOutException exception) {
            System.out.println(exception.getMessage());
        } catch (TooManyRequestsException exception) {
            System.out.println(exception.getMessage());
        } catch (UnauthorizedException exception) {
            System.out.println(exception.getMessage());
        } catch (NameSnipedResponse exception) {
            System.out.println(exception.getMessage());
        }
    }
}