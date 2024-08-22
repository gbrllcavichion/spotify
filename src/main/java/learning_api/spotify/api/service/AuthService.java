package learning_api.spotify.api.service;

import learning_api.spotify.api.dto.LoginRequest;
import learning_api.spotify.client.AuthSpotifyClient;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthSpotifyClient authSpotifyClient;

    public AuthService(AuthSpotifyClient authSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
    }

    public String getAccessToken(String clientId, String clientSecret) {
        var request = new LoginRequest("client_credentials", clientId, clientSecret);
        return authSpotifyClient.login(request).getAccessToken();
    }
}
