package shop.mtcoding.blog.reply;

import lombok.Data;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        private Integer boardId;
        private String content;

        public Reply toEntity(User user, Board board) {
            return Reply.builder()
                    .content(content)
                    .user(user)
                    .board(board)
                    .build();
        }
    }

    @Data
    public static class DeleteDTO {
        private Integer boardId;
    }
}
