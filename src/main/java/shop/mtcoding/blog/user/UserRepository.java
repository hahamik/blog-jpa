package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    /*
    1. createNativeQuery : 기본쿼리
    2. createQuery : JPA가 제공해주는 객체지향 쿼리
    3. createNamedQuery : Query Method는 함수 이름으로 쿼리 생성 , 메서드 이름 적으면 query가 발동함
    4. createEntityQuery :
     */

    public void save(User user) {
        em.persist(user);
    }

    // :username 에 키값을 넣어줌
    // 클래스이름 대문자
    public User findByUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }
}