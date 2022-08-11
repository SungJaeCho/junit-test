package site.coding.junitproject.web.dto;

import lombok.Setter;
import site.coding.junitproject.domain.Book;

@Setter
public class BookSaveRequestDto {
    private String title;
    private String author;

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }

}
