package toor.barrettbot.environment.discord.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import toor.barrettbot.environment.discord.messages.CustomEmbedBuilder;
import toor.barrettbot.environment.mojangapi.MojangProfile;
import toor.barrettbot.environment.sniper.sniper.main.Sniper;

public class SnipeCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        final String message = event.getMessage().getContentDisplay();
        if(message.equalsIgnoreCase("+help")) {
            event.getTextChannel().sendMessage(new CustomEmbedBuilder().generalInformation().build()).queue();
        }else if(message.equalsIgnoreCase("+delete")) {
            event.getTextChannel().sendMessage(new CustomEmbedBuilder().custom("Dieser Befehl ist noch in Entwicklung").build()).queue();
        }else if(message.equalsIgnoreCase("+snipe")) {
            event.getTextChannel().sendMessage(new CustomEmbedBuilder().custom("Dieser Befehl ist noch in Entwicklung").build()).queue();
        }
        if(message.toLowerCase().startsWith("+create")) {
            String[] args = message.split(" ");
            if(args.length == 7) {
                String time = message.split("-t ")[1];
                Sniper sniper = new Sniper(args[1], args[2], args[3], time, event.getMessage());
            }else {
                event.getTextChannel().sendMessage(new CustomEmbedBuilder().generalInformation().build()).queue();
            }
        }
        if(message.toLowerCase().startsWith("+history")) {
            String[] args = message.split(" ");
            String name = args[1];
            MojangProfile profile = new MojangProfile(name);
            event.getTextChannel().sendMessage(profile.get()).queue();
        }
    }
}
