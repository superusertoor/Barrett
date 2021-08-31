package toor.barrettbot.environment.mojangapi;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MojangTool {

    private JsonParser jsonParser = new JsonParser();
    private JsonElement element;
    private List<String> textures = new ArrayList<>();
    private Document doc;
    private static HashMap<String, Long> history = new HashMap<>();

    public String getUUID(String name) {
        try {
            doc = Jsoup.connect("https://api.mojang.com/users/profiles/minecraft/" + name + "?at=0").ignoreContentType(true).get();
            element = jsonParser.parse(doc.text());
            return element.getAsJsonObject().get("id").getAsString();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return "";
    }

    public String getName(String uuid) {
        try {
            element = jsonParser.parse(Jsoup.connect("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid).ignoreContentType(true).get().text());
            return element.getAsJsonObject().get("name").getAsString();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return "";
    }

    public List<String> getTextures(String uuid) {
        try {
            element = jsonParser.parse(Jsoup.connect("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid).ignoreContentType(true).get().text());
            textures.add(element.getAsJsonObject().get("properties").getAsJsonObject().getAsString());
            textures.add(element.getAsJsonObject().get("properties").getAsJsonObject().getAsString());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public HashMap<String, Long> getNameHistory(String uuid) {
        try {
            doc = Jsoup.connect("https://api.mojang.com/user/profiles/" + uuid + "/names").ignoreContentType(true).get();
            element = jsonParser.parse(doc.text());
            element.getAsJsonObject().get("name");
            System.out.println(element.getAsJsonObject().get("name"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
