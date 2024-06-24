package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.entity.Character;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
