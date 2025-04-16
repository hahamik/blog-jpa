package shop.mtcoding.blog.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardRequest {

    @Data
    public static class UpdateDTO {
        // name 값이 없으면 null, name 값이 있는데 안적으면 공백이다.
        // title= 제목&content=내용1 -> isPublic은 null이다.
        // title= 제목&content=내용1&isPublic -> isPublic은 ""이다.
        // title= 제목&content=내용1&isPublic= -> isPublic은 " "이다.
        @NotEmpty(message = "제목을 입력하세요") // null 안되고 space " " 안되고 ""안됨
        private String title;
        @NotBlank(message = "내용을 입력하세요")
        private String content;
        private String isPublic;
        // notempty, notblank, notnull 정리하기
        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .isPublic(isPublic == null ? false : true)
                    .user(user) // user객체 필요
                    .build();
        }
    }

    @Data
    public static class SaveDTO {
        @NotEmpty(message = "제목을 입력하세요")
        private String title;
        @NotEmpty(message = "내용을 입력하세요")
        private String content;
        private String isPublic;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .isPublic(isPublic == null ? false : true)
                    .user(user) // user객체 필요
                    .build();
        }
    }
}
