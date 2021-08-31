package toor.barrettbot.environment.mojangapi;

import java.util.HashMap;

public class UserProfile {

    String texture;
    String signature;
    MojangTool mojangTool = new MojangTool();
    private HashMap<String, Long> history = new HashMap<>();

    public UserProfile(String uuid) {
        this.history = mojangTool.getNameHistory(uuid);

        //        this.texture = mojangTool.getTextures(uuid).get(0);
        //this.signature = mojangTool.getTextures(uuid).get(1);
    }

    public HashMap<String, Long> getNameHistory() {
        return history;
    }

    public String getTexture() {
        return texture;
    }

    public String getSignature() {
        return signature;
    }
}
