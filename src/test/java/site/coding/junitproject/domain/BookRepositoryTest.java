package site.coding.junitproject.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //DB와 관련된 컴포넌트만 메모리에 로딩
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void 데이터준비() {
        //given
        String title = "junit";
        String author = "csj";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        bookRepository.save(book);
    }

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
    @Test
    public void 책목록보기_test() throws Exception {
        //given
        String title = "junit";
        String author = "csj";
        //when
        List<Book> books = bookRepository.findAll();
        //then
        assertEquals(title, books.get(0).getTitle());
        assertEquals(author, books.get(0).getAuthor());

    }

    // 3. 책 한건보기
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책한건보기_test() throws Exception {
        //given
        String title = "junit";
        String author = "csj";
        //when
        Book bookPS = bookRepository.findById(1L).get();
        //then
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }

    // 4. 책 수정

    // 5. 책 삭제
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책삭제_test() throws Exception {
        //given
        Long id = 1L;
        //when
        bookRepository.deleteById(id);
        //then
        assertFalse(bookRepository.findById(id).isPresent());
    }
}