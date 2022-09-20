package site.coding.junitproject.web.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CMResponseDto<T> {
    private Integer code; // 1.성공, -1.실패
    private String message; //에러메시지, 성공에 대한 메시지
    private T body;

    @Builder
    public CMResponseDto(Integer code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }
}
