package learning_api.spotify.api.controller;

import learning_api.spotify.api.dto.Album;
import learning_api.spotify.api.dto.LoginRequest;
import learning_api.spotify.client.AlbumSpotifyClient;
import learning_api.spotify.client.AuthSpotifyClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;
    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    public AlbumController(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> loginRequest() {
        var request = new LoginRequest(
                "client_credentials",
                clientId,
                clientSecret
        );

        var token = authSpotifyClient.login(request).getAccessToken();
        var response = albumSpotifyClient.getReleases("Bearer " + token);

        return ResponseEntity.ok(response.getAlbums().getItems());
    }
}
