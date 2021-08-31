package toor.barrettbot.environment.mojangapi;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MojangProfile {

    private String uuid;
    private String name;
    private HashMap<HashMap, Long> history = new HashMap<>();
    private MojangTool mojangTool = new MojangTool();
    private EmbedBuilder embedBuilder = new EmbedBuilder();
    private UserProfile userProfile;

    public MojangProfile(String name) {
        this.uuid = mojangTool.getUUID(name);
        this.name = mojangTool.getName(uuid);
        this.userProfile = new UserProfile(uuid);
        embedBuilder.setDescription("> Profil von " + name);
        embedBuilder.addField("Name", "```" + name + "```", true);
        embedBuilder.addField("UUID", "```" + uuid + "```", true);
        for(Map.Entry<String, Long> t : userProfile.getNameHistory().entrySet()) {

        }
    }

    public MojangProfile(UUID uuid) {

    }

    public String getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public HashMap<HashMap, Long> getHistory() {
        return history;
    }

    public MessageEmbed get() {
        return embedBuilder.build();
    }

}
