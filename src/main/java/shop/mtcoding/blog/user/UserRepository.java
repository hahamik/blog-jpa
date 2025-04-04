package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    //jpql은 통역가
    // jpql 로 짜면 객체 지향이기 때문에 짜기 쉬움
    public User findById(int id) {
        return em.find(User.class, id);
    }

    /*
    1. createNativeQuery : 기본쿼리
    2. createQuery : JPA가 제공해주는 객체지향 쿼리
    3. createNamedQuery : Query Method는 함수 이름으로 쿼리 생성 , 메서드 이름 적으면 query가 발동함
    4. createEntityQuery :
     */

    public void save(User user) {
        em.persist(user); // 3. user 영속 객체
        // 4. user 는 데이터베이스와 동기화 됨
    }


    // :username 에 키값을 넣어줌
    // 클래스이름 대문자
    public User findByUsername(String username) {
        return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}