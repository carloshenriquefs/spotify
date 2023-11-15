package tech.buildrun.spotify.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.buildrun.spotify.client.AuthSpotifyClient;
import tech.buildrun.spotify.client.LoginRequest;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<String> helloWorld() {
        var request = new LoginRequest(
                "client_credentials",
                "ba5898a04bc149389d3d8eb3adc3c733",
                "66ad5fcf44a447978e4a89b5686e6f98"
        );

        return ResponseEntity.ok(authSpotifyClient.login(request).getAccessToken());
    }

}
