package toor.barrettbot.environment.main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.SelfUser;
import toor.barrettbot.environment.discord.commands.SnipeCommand;
import toor.barrettbot.environment.discord.events.StartEvent;

import javax.security.auth.login.LoginException;

public class Barrett {

    private static SelfUser selfUser;
    private static Guild g;

    public static void main(String[] args) throws LoginException {
        JDA builder = JDABuilder.createDefault("token")
                .setAutoReconnect(true)
                .addEventListeners(new SnipeCommand())
                .addEventListeners(new StartEvent())
                .build();
    }

    public static Guild getGuild() {
        return g;
    }

    public static void setGuild(Guild guild) {
        g = guild;
    }


    public static SelfUser getSelfUser() {
        return selfUser;
    }

}
