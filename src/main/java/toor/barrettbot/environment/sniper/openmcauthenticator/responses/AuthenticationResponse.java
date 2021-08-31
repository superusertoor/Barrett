package toor.barrettbot.environment.sniper.openmcauthenticator.responses;

import toor.barrettbot.environment.sniper.openmcauthenticator.Profile;

public class AuthenticationResponse extends LoginResponse {

  private Profile[] availableProfiles;

  public AuthenticationResponse(String accessToken, String clientToken, Profile selectedProfile, Profile[] availableProfiles) {
    super(accessToken, clientToken, selectedProfile);
    this.availableProfiles = availableProfiles;
  }

  public Profile[] getAvailableProfiles() {
    return availableProfiles;
  }

}
