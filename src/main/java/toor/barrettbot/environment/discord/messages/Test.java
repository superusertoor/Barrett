package toor.barrettbot.environment.discord.messages;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Test {

    public static HashMap<TextChannel, Test> tests = new HashMap<>();
    private EmbedBuilder embedBuilder = new EmbedBuilder().setColor(Color.decode("0x72407d"));
    private int seconds = 0;
    private Message message;
    private TextChannel textChannel;
    private Timer timer = new Timer();
    private String email= "";
    private String pass = "";
    private String wantedName= "";
    private User user;
    private String dropDate;

    public Test(Message message, String email, String pass, String wantedName, String dropDate) {
        this.textChannel = message.getTextChannel();
        this.wantedName = wantedName;
        this.email = email;
        this.user = message.getAuthor();
        this.dropDate = dropDate;
        for(int i = 0; i < pass.length(); i++) {
            this.pass+="*";
        }
        tests.put(textChannel, this);
    }

    public Test setSeconds(int secs) {
        this.seconds = secs;
        return this;
    }

    public Test sendMessage() {
        embedBuilder.setDescription("> Snipe von " + user.getAsMention() + "\n\n\uD83D\uDD30 **Status: Anfang**");
        embedBuilder.addField("⠀", " » **Übersicht**", false);
        embedBuilder.addField("Autorisiert","```" + user.getAsTag() + "```", true);
        embedBuilder.addField("Datum des Drops","```" + dropDate + "```", true);
        embedBuilder.addField("⠀", " » **Account Informationen**", false);
        embedBuilder.addField("Name", "```" + wantedName + "```", true);
        embedBuilder.addField("Email", "```" + email + "```", true);
        embedBuilder.addField("Passwort", "```" + pass + "```", true);
        embedBuilder.addField("Phase", "\uD83D\uDD01", true);
        message = textChannel.sendMessage(embedBuilder.build()).complete();
        return this;
    }

    public void setDescription(String string) {
        embedBuilder.setDescription(string);
        message.editMessage(message).setEmbeds(embedBuilder.build()).queue();
    }

    public void stopCounter() {
        timer.cancel();
        timer.purge();
    }

    public Test startUpdating()  {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (seconds < 10) {
                    embedBuilder.setDescription("> Snipe von " + user.getAsMention() + "\n\n\uD83D\uDD30 **Beginnt in " + seconds + " Sekunden**");
                    message.editMessage(message).setEmbeds(embedBuilder.build()).queue();
                    if (seconds == 0) {
                        timer.cancel();
                        timer.purge();
                    }
                }
                seconds--;
            }
        }, 0, 1000);
        return this;
    }
}
