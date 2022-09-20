package site.coding.junitproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.coding.junitproject.web.dto.response.BookResponseDto;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ")
    private Long id;


//    @GenericGenerator(name = "id2", strategy = "site.coding.junitproject.domain.BookIdGenerator")
//    @Column(columnDefinition="serial")
//    @GeneratedValue(generator = "id2")
//    private String id2;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 20, nullable = false)
    private String author;

    @Builder
    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public void update(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public BookResponseDto toDto() {
        return BookResponseDto.builder()
                .id(id)
                .title(title)
                .author(author)
                .build();
    }
}
