package learning_api.spotify.api.dto;

import java.util.List;

public class AlbumWrapper {

    private List<Album> items;

    public AlbumWrapper(List<Album> items) {
        this.items = items;
    }

    public AlbumWrapper() {
    }

    public List<Album> getItems() {
        return items;
    }

    public void setItems(List<Album> items) {
        this.items = items;
    }
}
