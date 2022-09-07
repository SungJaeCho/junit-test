package site.coding.junitproject.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.coding.junitproject.domain.Book;

@NoArgsConstructor
@Getter
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;

    //안씀
    public BookResponseDto toDto(Book bookPS) {
        id = bookPS.getId();
        title = bookPS.getTitle();
        author = bookPS.getAuthor();
        return this;
    }

    @Builder
    public BookResponseDto(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}
