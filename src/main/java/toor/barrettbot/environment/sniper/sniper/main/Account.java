package toor.barrettbot.environment.sniper.sniper.main;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import toor.barrettbot.environment.sniper.sniper.exceptions.TooLessProxiesException;
import toor.barrettbot.environment.sniper.sniper.sniper.Authentication;

import java.net.Proxy;
import java.util.List;

public class Account {

    private String password;
    private String email;
    private String wantedName;
    private Authentication authentication;
    private Long dropTime;
    private List<Proxy> proxies;
    private boolean useProxies;
    private String accessToken;
    private User user;
    private TextChannel textChannel;

    public Account() {

    }

    public Account setAccessToken(String token) {
        this.accessToken = token;
        return this;
    }

    public TextChannel getChannel() {
        return textChannel;
    }

    public void setChannel(TextChannel textChannel) {
        this.textChannel = textChannel;
    }

    public void setDiscordUser(User user) {
        this.user = user;
    }

    public User getDiscordUser() {
        return user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void executeSnipe() {
        new SnipeRequest(this, getAccessToken()).start();
    }

    public String getPassword() {
        return password;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean usingProxies() {
        return useProxies;
    }

    public void setUseProxies(boolean useProxies) {
        this.useProxies = useProxies;
    }

    public String getEmail() {
        return email;
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }


    public String getWantedName() {
        return wantedName;
    }

    public void setWantedName(String wantedName) {
        this.wantedName = wantedName;
    }

    public Account setAuthentication(Authentication authentication) {
        this.authentication = authentication;
        return this;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public Long getDropTime() {
        return dropTime;
    }

    public void setDropTime(long dropTime) {
        this.dropTime = dropTime;
    }

    public void setProxies(List<Proxy> proxies) throws TooLessProxiesException {
        //if(proxies.size() == 0) {
        //    throw new TooLessProxiesException();
        //}
        this.proxies = proxies;
    }

    public List<Proxy> getProxies() {
        return proxies;
    }

}