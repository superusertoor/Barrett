package toor.barrettbot.environment.sniper.openmcauthenticator.responses;

import java.util.Map;

public class RequestResponse {

  private int responseCode = -1;
  private Map<String, Object> data;

  public RequestResponse(int responseCode, Map<String, Object> data) {
    this.responseCode = responseCode;
    this.data = data;
  }

  public int getResponseCode() {
    return responseCode;
  }

  public boolean isSuccessful() {
    return responseCode == 200 || responseCode == 204;
  }

  public Map<String, Object> getData() {
    return data;
  }
}
