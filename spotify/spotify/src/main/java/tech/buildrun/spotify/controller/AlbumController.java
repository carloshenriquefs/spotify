package tech.buildrun.spotify.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.buildrun.spotify.client.Album;
import tech.buildrun.spotify.client.AlbumSpotifyClient;
import tech.buildrun.spotify.client.AuthSpotifyClient;
import tech.buildrun.spotify.client.LoginRequest;

import java.util.List;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> helloWorld() {
        var request = new LoginRequest(
                "client_credentials",
                "ba5898a04bc149389d3d8eb3adc3c733",
                "66ad5fcf44a447978e4a89b5686e6f98"
        );

        var token = authSpotifyClient.login(request).getAccessToken();

        var response = albumSpotifyClient.getRelease("Bearer " + token);

        return ResponseEntity.ok(response.getAlbums().getItems());
    }

}
