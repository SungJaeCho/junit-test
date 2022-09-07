package site.coding.junitproject.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.coding.junitproject.domain.BookRepository;
import site.coding.junitproject.util.MailSender;
import site.coding.junitproject.web.dto.BookResponseDto;
import site.coding.junitproject.web.dto.BookSaveRequestDto;

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
        Assertions.assertThat(dto.getTitle()).isEqualTo(bookResponseDto.getTitle());
        Assertions.assertThat(dto.getAuthor()).isEqualTo(bookResponseDto.getAuthor());

    }

}
