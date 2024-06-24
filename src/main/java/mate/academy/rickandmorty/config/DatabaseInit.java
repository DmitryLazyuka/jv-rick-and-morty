package mate.academy.rickandmorty.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalApiResponseDto;
import mate.academy.rickandmorty.entity.Character;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.util.DatabaseUtil;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DatabaseInit {
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterService service;
    private final DatabaseUtil databaseUtil;

    @PostConstruct
    public void init() {
        if (databaseUtil.getCount() > 0) {
            return;
        }
        List<Character> characters = fetchCharacters();
        service.saveAll(characters);
    }

    private List<Character> fetchCharacters() {
        List<Character> characters = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            String url = "https://rickandmortyapi.com/api/character";
            ExternalApiResponseDto serializedResponse;
            do {
                HttpRequest request = HttpRequest.newBuilder()
                        .GET()
                        .uri(new URI(url))
                        .build();
                HttpResponse<String> response =
                        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                serializedResponse =
                        objectMapper.readValue(response.body(), ExternalApiResponseDto.class);
                characters.addAll(serializedResponse.results().stream()
                        .map(characterMapper::externalApiResultToCharacter)
                        .toList());
                url = serializedResponse.info().next();
            } while (url != null);
            return characters;
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
