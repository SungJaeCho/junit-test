package site.coding.junitproject.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //DB와 관련된 컴포넌트만 메모리에 로딩
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    // 1. 책등록
    @Test
    public void 책등록_Test() throws Exception {
        //given
        String title = "junit5";
        String author = "성재";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        //when
        Book bookPS = bookRepository.save(book);
        //then
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }

    // 2. 책 목록보기

    // 3. 책 한건보기

    // 4. 책 수정

    // 5. 책 삭제
}