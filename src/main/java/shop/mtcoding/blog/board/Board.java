package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private Boolean isPublic;

    // Many는 Board를 뜻함 One는 user 반대면? OnetoMany
    //이렇게 하면 db에 fk가 만들어진다.
    // 오브젝트 릴레이션 매핑
    // 연관관계를 맺으면 자동으로 join 해준다.
    // join을해서 user 객체에까지 mapping 해서 알아서 넣어줌 대박
    @ManyToOne(fetch = FetchType.EAGER) // lazy 는 호출될 때 가져옴 지연로딩 그럼 이거 어디에 활용함?
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Board(Integer id, String title, String content, Boolean isPublic, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isPublic = isPublic;
        this.user = user;
        this.createdAt = createdAt;
    }
}