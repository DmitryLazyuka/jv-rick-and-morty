package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record ExternalApiResponseDto(
        Info info,
        List<Result> results
){
}
