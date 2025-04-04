package shop.mtcoding.blog._core;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Resp<T> {
    private Integer status;
    private String msg;
    private T body;

    // TODO 이거 다시 공부
    public static <B> Resp<?> ok(B body) {
        return new Resp<B>(200, "성공", body);
    }

    public static Resp<?> fail(Integer status, String msg) {
        return new Resp<>(status, msg, null);
    }
}