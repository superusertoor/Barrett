package toor.barrettbot.environment.discord.messages;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class CustomEmbedBuilder {

    private EmbedBuilder embedBuilder = new EmbedBuilder().setColor(Color.decode("0x0A1119"));

    public CustomEmbedBuilder generalInformation() {
        embedBuilder.addField("+Help", "```Zeigt diese Übersicht an```", true);
        embedBuilder.addField("+Snipe", "```Zeigt deinen aktuellen Snipe an```", true);
        embedBuilder.addField("+Create", "```Erstellt einen Snipe für dich```", true);
        embedBuilder.addField("+Delete", "```Löscht deinen Snipe```", true);
        embedBuilder.setDescription("> **Information**\n » Befehlsübersicht\n");
        return this;
    }

    public CustomEmbedBuilder error(String error) {
        embedBuilder.addField("Fehler", "**" + error + "**", true);
        embedBuilder.setDescription("> **Fehler**\n » Ein Fehler ist aufgetreten\n");
        return this;
    }

    public CustomEmbedBuilder custom(String error) {
        embedBuilder.addField("Information", "**" + error + "**", true);
        embedBuilder.setDescription("> **Information**\n » Generelle Information\n");
        return this;
    }

    public MessageEmbed build() {
        return embedBuilder.build();
    }

}
