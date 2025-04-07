package shop.mtcoding.blog.board;

import lombok.Data;

import java.sql.Timestamp;

public class BoardResponse {
    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Boolean isPublic;
        private Boolean isOwner;
        private String username;
        private Timestamp createdAt;

        public DetailDTO(Board board, Integer sessionUserId) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.isPublic = board.getIsPublic();
            this.isOwner = sessionUserId == board.getUser().getId();
//            this.isOwner = (sessionUserId != null && sessionUserId.equals(board.getUser().getId()));
            this.username = board.getUser().getUsername();
            this.createdAt = board.getCreatedAt();
        }
    }


}
