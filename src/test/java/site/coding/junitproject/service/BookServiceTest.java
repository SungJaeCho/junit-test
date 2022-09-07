package site.coding.junitproject.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.coding.junitproject.domain.Book;
import site.coding.junitproject.domain.BookRepository;
import site.coding.junitproject.util.MailSender;
import site.coding.junitproject.web.dto.BookResponseDto;
import site.coding.junitproject.web.dto.BookSaveRequestDto;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //가짜 메모리 환경
class BookServiceTest {

    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private MailSender mailSender;


    // 문제점 -> 서비스만 테스트하고 싶은데, 레포지토리까지 같이 테스트됨
    @Test
    public void 책등록하기_테스트() throws Exception {
        //given
        BookSaveRequestDto dto = new BookSaveRequestDto();
        dto.setTitle("화산귀환");
        dto.setAuthor("저자");

        //stub
        when(bookRepository.save(any())).thenReturn(dto.toEntity());
        when(mailSender.send()).thenReturn(true);

        //when
        BookResponseDto bookResponseDto = bookService.책등록하기(dto);
        //then
//        assertEquals(dto.getTitle(), bookResponseDto.getTitle());
//        assertEquals(dto.getAuthor(), bookResponseDto.getAuthor());
        assertThat(bookResponseDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookResponseDto.getAuthor()).isEqualTo(dto.getAuthor());

    }

    @Test
    public void 책목록보기_테스트() throws Exception {
        //given (파라미터로 들어올데이터)


        //stub (가설)
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1L, "junit강의", "코딩머신"));
        books.add(new Book(2L, "화산귀환", "저자"));
        when(bookRepository.findAll()).thenReturn(books);

        //when
        List<BookResponseDto> bookResponseDtoList = bookService.책목록보기();
        //then
        assertThat(bookResponseDtoList.get(0).getTitle()).isEqualTo("junit강의");
        assertThat(bookResponseDtoList.get(0).getAuthor()).isEqualTo("코딩머신");
        assertThat(bookResponseDtoList.get(1).getTitle()).isEqualTo("화산귀환");
        assertThat(bookResponseDtoList.get(1).getAuthor()).isEqualTo("저자");

    }

}
