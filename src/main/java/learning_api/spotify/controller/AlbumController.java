package learning_api.spotify.controller;

import learning_api.spotify.client.AuthSpotifyClient;
import learning_api.spotify.client.LoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;
    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    public AlbumController(AuthSpotifyClient authSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<String> loginRequest() {
        var request = new LoginRequest(
                "client_credentials",
                clientId,
                clientSecret
        );

        return ResponseEntity.ok(authSpotifyClient.login(request).getAccessToken());
    }
}
