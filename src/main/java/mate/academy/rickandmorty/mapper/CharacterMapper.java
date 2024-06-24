package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.Result;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.entity.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterDto toCharacterDto(Character character);

    Character dtoToCharacter(CharacterDto characterDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "externalId")
    Character externalApiResultToCharacter(Result result);
}
