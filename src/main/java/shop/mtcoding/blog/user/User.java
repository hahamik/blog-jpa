package shop.mtcoding.blog.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;

    @CreationTimestamp //@CreationTimestamp insert 할 때 now 가 자동으로 들어감
    private Timestamp createdAt;

    @Builder // @B
    public User(Integer id, String username, String password, String email, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }
    // 마지막에 build로 닫아주면 됨 lombok에서 지원함
    // 풀 생성자를 만들고 위에 builer를 붙이면 해줌 ㄹㅈㄷ
//    User u = User.builder().username(username).password(password).email(email).build();
}