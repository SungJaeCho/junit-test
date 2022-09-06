package site.coding.junitproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import site.coding.junitproject.domain.BookRepository;
import site.coding.junitproject.util.MailSenderStub;
import site.coding.junitproject.web.dto.BookResponseDto;
import site.coding.junitproject.web.dto.BookSaveRequestDto;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;


    // 문제점 -> 서비스만 테스트하고 싶은데, 레포지토리까지 같이 테스트됨
    @Test
    public void 책등록하기_테스트() throws Exception {
        //given
        BookSaveRequestDto dto = new BookSaveRequestDto();
        dto.setTitle("화산귀환");
        dto.setAuthor("저자");

        //stub
        MailSenderStub mailSenderStub = new MailSenderStub();
        //when
        BookService bookService = new BookService(bookRepository, mailSenderStub);
        BookResponseDto bookResponseDto = bookService.책등록하기(dto);
        //then
        assertEquals(dto.getTitle(), bookResponseDto.getTitle());
        assertEquals(dto.getAuthor(), bookResponseDto.getAuthor());
    }

}
