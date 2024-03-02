package h9w.me.transactional.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDTO<D> {
    private boolean result;
    private String resultCode;
    private D data;

    public static <D> ResponseDTO<D> success(String resultCode, D data) {
        return new ResponseDTO<>(true, resultCode, data);
    }

    public static <D> ResponseDTO<D> fail(String resultCode, D data) {
        return new ResponseDTO<>(false, resultCode, data);
    }
}
