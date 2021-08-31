package toor.barrettbot.environment.sniper.sniper.main;

import net.dv8tion.jda.api.entities.Message;
import toor.barrettbot.environment.discord.messages.CustomEmbedBuilder;
import toor.barrettbot.environment.discord.messages.Test;
import toor.barrettbot.environment.sniper.openmcauthenticator.OpenMCAuthenticator;
import toor.barrettbot.environment.sniper.openmcauthenticator.exceptions.AuthenticationUnavailableException;
import toor.barrettbot.environment.sniper.openmcauthenticator.exceptions.RequestException;
import toor.barrettbot.environment.sniper.sniper.exceptions.TooLessProxiesException;

import java.net.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sniper {

    public static List<Proxy> proxies = new ArrayList<>();
    private Account account;

    public Sniper(String wantedName, String loginPassword, String loginEmail, String time, Message message) {
        try {
            account = new Account();
            account.setAccessToken(OpenMCAuthenticator.authenticate(loginEmail, loginPassword).getAccessToken());
            account.setWantedName(wantedName);
            account.setUseProxies(false);
            if (account.usingProxies()) {
                account.setProxies(proxies);
            }
            account.setChannel(message.getTextChannel());
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date current = new Date(System.currentTimeMillis());
            Date dropTime = format.parse(time);
            long diff = dropTime.getTime() - current.getTime();
            Test test = new Test(message, loginEmail, loginPassword, wantedName, time);
            test.setSeconds((int) diff / 1000);
            test.sendMessage();
            test.startUpdating();
            if (diff - 600 > 0L) {
                Thread.sleep(diff - 600);
                test.stopCounter();
                test.setDescription("Der Snipe beginnt");
                account.executeSnipe();
            }
        } catch (ParseException e) {
            account.getChannel().sendMessage(new CustomEmbedBuilder().error("Ein Fehler ist aufgetreten").build()).queue();
        }catch (InterruptedException e) {
            account.getChannel().sendMessage(new CustomEmbedBuilder().error("Ein Fehler ist aufgetreten").build()).queue();
        }catch (TooLessProxiesException e) {
            account.getChannel().sendMessage(new CustomEmbedBuilder().error("Ein Fehler ist aufgetreten").build()).queue();
        }catch (RequestException e) {
            account.getChannel().sendMessage(new CustomEmbedBuilder().error("Ein Fehler ist aufgetreten").build()).queue();
        }catch (AuthenticationUnavailableException e) {
            account.getChannel().sendMessage(new CustomEmbedBuilder().error("Die Anmeldedaten sind ungültig").build()).queue();
        }
    }
}