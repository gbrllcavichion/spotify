package learning_api.spotify.api.controller;

import learning_api.spotify.api.dto.Album;
import learning_api.spotify.api.dto.LoginRequest;
import learning_api.spotify.api.service.AlbumService;
import learning_api.spotify.api.service.AuthService;
import learning_api.spotify.client.AlbumSpotifyClient;
import learning_api.spotify.client.AuthSpotifyClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("spotify/api")
public class AlbumController {

    private final AuthService authService;
    private final AlbumService albumService;
    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    public AlbumController(AuthService authService, AlbumService albumService) {
        this.authService = authService;
        this.albumService = albumService;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getAlbums() {
        try {
            String token = authService.getAccessToken(clientId, clientSecret);
            List<Album> albums = albumService.getReleases("Bearer " + token);
            return ResponseEntity.ok(albums);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch albums", e);
        }
    }
}
