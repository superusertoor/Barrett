package toor.barrettbot.environment.sniper.openmcauthenticator;

import toor.barrettbot.environment.sniper.openmcauthenticator.exceptions.*;
import toor.barrettbot.environment.sniper.openmcauthenticator.responses.AuthenticationResponse;
import toor.barrettbot.environment.sniper.openmcauthenticator.responses.ErrorResponse;
import toor.barrettbot.environment.sniper.openmcauthenticator.responses.RefreshResponse;
import toor.barrettbot.environment.sniper.openmcauthenticator.responses.RequestResponse;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;

public class OpenMCAuthenticator {

  public static AuthenticationResponse authenticate(String username, String password, String clientToken, Proxy proxy) throws RequestException, AuthenticationUnavailableException {
    RequestResponse result = sendJsonPostRequest(getRequestUrl("authenticate"), JsonUtils.credentialsToJson(username, password, clientToken), proxy);
    if (result.isSuccessful()) {
      String accessToken = (String) result.getData().get("accessToken");
      String rClientToken = (String) result.getData().get("clientToken");
      Profile selectedProfile = JsonUtils.gson.fromJson(JsonUtils.gson.toJson(result.getData().get("selectedProfile")), Profile.class);
      Profile[] availableProfiles = JsonUtils.gson.fromJson(JsonUtils.gson.toJson(result.getData().get("availableProfiles")), Profile[].class);
      return new AuthenticationResponse(accessToken, rClientToken, selectedProfile, availableProfiles);
    } else {
      ErrorResponse errorResponse = JsonUtils.gson.fromJson(JsonUtils.gson.toJson(result.getData()), ErrorResponse.class);
      if (result.getData().get("cause") != null && ((String) (result.getData().get("cause"))).equalsIgnoreCase("UserMigratedException")) {
        throw new UserMigratedException(errorResponse);
      } else {
        throw new InvalidCredentialsException(errorResponse);
      }
    }
  }

  public static AuthenticationResponse authenticate(String username, String password) throws RequestException, AuthenticationUnavailableException {
    return authenticate(username, password, null, null);
  }


  public static RefreshResponse refresh(String accessToken, String clientToken, Proxy proxy) throws RequestException, AuthenticationUnavailableException {
    RequestResponse result = sendJsonPostRequest(getRequestUrl("refresh"), JsonUtils.tokenToJson(accessToken, clientToken), proxy);
    if (result.isSuccessful()) {
      String newAccessToken = (String) result.getData().get("accessToken");
      String rClientToken = (String) result.getData().get("clientToken");
      Profile selectedProfile = JsonUtils.gson.fromJson(JsonUtils.gson.toJson(result.getData().get("selectedProfile")), Profile.class);
      return new RefreshResponse(newAccessToken, rClientToken, selectedProfile);
    } else {
      ErrorResponse errorResponse = JsonUtils.gson.fromJson(JsonUtils.gson.toJson(result.getData()), ErrorResponse.class);
      throw new InvalidTokenException(errorResponse);
    }
  }

  public static RefreshResponse refresh(String accessToken, String clientToken) throws RequestException, AuthenticationUnavailableException {
    return refresh(accessToken, clientToken, null);
  }


  public static boolean validate(String accessToken, String clientToken, Proxy proxy) throws RequestException, AuthenticationUnavailableException {
    RequestResponse result = sendJsonPostRequest(getRequestUrl("validate"), JsonUtils.tokenToJson(accessToken, clientToken), proxy);
    if (result.isSuccessful()) {
      return true;
    } else {
      ErrorResponse errorResponse = JsonUtils.gson.fromJson(JsonUtils.gson.toJson(result.getData()), ErrorResponse.class);
      throw new InvalidTokenException(errorResponse);
    }
  }

  public static boolean validate(String accessToken, String clientToken) throws RequestException, AuthenticationUnavailableException {
    return validate(accessToken, clientToken, null);
  }

  public static boolean validate(String accessToken) throws RequestException, AuthenticationUnavailableException {
    return validate(accessToken, null, null);
  }

  public static boolean invalidate(String accessToken, String clientToken, Proxy proxy) throws RequestException, AuthenticationUnavailableException {
    RequestResponse result = sendJsonPostRequest(getRequestUrl("invalidate"), JsonUtils.tokenToJson(accessToken, clientToken), proxy);
    if (result.isSuccessful()) {
      return true;
    } else {
      ErrorResponse errorResponse = JsonUtils.gson.fromJson(JsonUtils.gson.toJson(result.getData()), ErrorResponse.class);
      throw new InvalidTokenException(errorResponse);
    }
  }


  public static boolean invalidate(String accessToken, String clientToken) throws RequestException, AuthenticationUnavailableException {
    return invalidate(accessToken, clientToken, null);
  }


  public static boolean signout(String username, String password, String clientToken, Proxy proxy) throws RequestException, AuthenticationUnavailableException {
    RequestResponse result = sendJsonPostRequest(getRequestUrl("signout"), JsonUtils.credentialsToJson(username, password, clientToken), proxy);
    if (result.isSuccessful()) {
      return true;
    } else {
      ErrorResponse errorResponse = JsonUtils.gson.fromJson(JsonUtils.gson.toJson(result.getData()), ErrorResponse.class);
      if (result.getData().get("cause") != null && ((String) (result.getData().get("cause"))).equalsIgnoreCase("UserMigratedException")) {
        throw new UserMigratedException(errorResponse);
      } else {
        throw new InvalidCredentialsException(errorResponse);
      }
    }
  }

  public static boolean signout(String username, String password, String clientToken) throws RequestException, AuthenticationUnavailableException {
    return signout(username, password, clientToken, null);
  }


  public static boolean signout(String username, String password) throws RequestException, AuthenticationUnavailableException {
    return signout(username, password, null, null);
  }

  private static URL getRequestUrl(String request) {
    try {
      return new URL("https://authserver.mojang.com/" + request);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private static RequestResponse sendJsonPostRequest(URL requestUrl, String payload, Proxy proxy) throws AuthenticationUnavailableException {
    HttpsURLConnection connection = null;
    try {
      byte[] payloadBytes = payload.getBytes("UTF-8");
      connection = (HttpsURLConnection) (proxy != null ? requestUrl.openConnection(proxy) : requestUrl.openConnection());
      connection.setDoOutput(true);
      connection.setDoInput(true);
      connection.setInstanceFollowRedirects(false);
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Accept-Charset", "UTF-8");
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("Content-Length", String.valueOf(payloadBytes.length));
      connection.setUseCaches(false);
      OutputStream out = connection.getOutputStream();
      out.write(payloadBytes, 0, payloadBytes.length);
      out.close();

      int responseCode = connection.getResponseCode();
      String line;
      BufferedReader reader = null;
      String response;
      switch (responseCode) {
        case 200:
          reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
          response = reader.readLine();
          break;
        case 204:
          response = "";
          break;
        default:
          reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8"));
          response = reader.readLine();
          break;
      }
      if (reader != null) {
        reader.close();
      }
      Map<String, Object> map = JsonUtils.gson.fromJson(response, JsonUtils.stringObjectMap);
      return new RequestResponse(responseCode, map);
    } catch (Exception e) {
      e.printStackTrace();
      throw new AuthenticationUnavailableException(null);
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
  }
}
