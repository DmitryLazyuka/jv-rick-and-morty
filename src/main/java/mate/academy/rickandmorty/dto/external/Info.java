package mate.academy.rickandmorty.dto.external;

public record Info(
        int count,
        int pages,
        String next,
        String prev
){
}
