package toor.barrettbot.environment.sniper.sniper.sniper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

public class SnipeUtil {

    public static String getReleaseTime(String name) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://namemc.com/name/" + name).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null)
                sb.append(line);
            String[] removeh = sb.toString().split("\"countdown-timer\" data-datetime=\"");
            String[] datenr = removeh[1].split(".000Z\"");
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String[] releasetime2 = datenr[0].split("T");
            String[] releasetime3 = releasetime2[0].split("-");
            final String releasetime = releasetime3[1] + "/" + releasetime3[2] + "/" + releasetime3[0] + " " + releasetime2[1];
            return releasetime;
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}