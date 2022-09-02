package site.coding.junitproject.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import site.coding.junitproject.domain.Book;

@NoArgsConstructor
@Getter
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;

    public BookResponseDto toDto(Book bookPS) {
        id = bookPS.getId();
        title = bookPS.getTitle();
        author = bookPS.getAuthor();
        return this;
    }

}
