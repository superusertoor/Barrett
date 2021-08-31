package toor.barrettbot.environment.discord.events;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import toor.barrettbot.environment.discord.main.Barrett;

public class StartEvent extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        Barrett.setGuild(event.getGuild());
    }
}
