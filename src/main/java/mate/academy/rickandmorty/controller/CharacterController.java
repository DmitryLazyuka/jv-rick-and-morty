package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Character controller", description = "Endpoints for managing characters")
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get random character")
    @GetMapping("/random")
    public CharacterDto test() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Search character by name")
    @GetMapping("/search")
    public List<CharacterDto> searchByName(@RequestParam("name") String name,
                                           @PageableDefault Pageable pageable) {
        return characterService.findCharacterByName(name, pageable);
    }
}
