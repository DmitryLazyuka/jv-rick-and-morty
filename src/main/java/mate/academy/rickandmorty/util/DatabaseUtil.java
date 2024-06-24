package mate.academy.rickandmorty.util;

import lombok.Getter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DatabaseUtil {
    private final CharacterRepository characterRepository;

    private Long count;

    @Autowired
    public DatabaseUtil(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
        initializeCount();
    }

    private void initializeCount() {
        count = characterRepository.count();
    }
}
