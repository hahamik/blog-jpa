package shop.mtcoding.blog.user;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


public class UserRequest {
    @Data
    public static class UserJoinDTO {
        private String username;
        private String password;
        private String email;
        @CreationTimestamp
        private Timestamp createAt;
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }
}
