package learning_api.spotify.api.service;

import learning_api.spotify.api.dto.Album;
import learning_api.spotify.client.AlbumSpotifyClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumService(AlbumSpotifyClient albumSpotifyClient) {
        this.albumSpotifyClient = albumSpotifyClient;
    }

    public List<Album> getReleases(String token) {
        return albumSpotifyClient.getReleases(token).getAlbums().getItems();
    }
}