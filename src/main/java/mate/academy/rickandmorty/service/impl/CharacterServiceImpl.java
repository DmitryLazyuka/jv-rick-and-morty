package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.entity.Character;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.util.DatabaseUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final Random RANDOM = new Random();
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final DatabaseUtil databaseUtil;

    @Override
    public void saveAll(List<Character> characters) {
        characterRepository.saveAll(characters);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        Long randomIndex = RANDOM.nextLong(databaseUtil.getCount());
        Character character = characterRepository.findById(randomIndex)
                .orElseThrow(EntityNotFoundException::new);
        return characterMapper.toCharacterDto(character);
    }

    @Override
    public List<CharacterDto> findCharacterByName(String name, Pageable pageable) {
        return characterRepository.findByNameContainingIgnoreCase(name, pageable)
                .stream()
                .map(characterMapper::toCharacterDto)
                .toList();
    }
}
