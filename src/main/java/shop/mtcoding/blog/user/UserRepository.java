package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;


    /*
        1. createNativeQuery -> 기본쿼리
        2. createQuery -> JPA가 제공해주는 객체지향 쿼리
        3. NamedQuery -> Query Method는 함수 이름으로 쿼리 생성 - 하지마요!!
        4. EntityGraph -> 지금 이해못함
     */
    public void save(User user) {
        em.persist(user);
    }

    public User findByUsername(String username) {
        try {
            return em.createQuery("select u from User u where u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public User findByUserId(Integer userId) {
        return em.find(User.class, userId);
    }

//    public void updateById() {
//        Query query = em.createQuery("update User u set u.username = :username");
//    }
}
