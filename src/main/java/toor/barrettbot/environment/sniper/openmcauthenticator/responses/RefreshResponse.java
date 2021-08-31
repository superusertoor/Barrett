package toor.barrettbot.environment.sniper.openmcauthenticator.responses;

import toor.barrettbot.environment.sniper.openmcauthenticator.Profile;

public class RefreshResponse extends LoginResponse {

  public RefreshResponse(String accessToken, String clientToken, Profile selectedProfile) {
    super(accessToken, clientToken, selectedProfile);
  }

}
