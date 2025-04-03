package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;


    public void join(String username, String password, String email) {
        Query query = em.createNativeQuery("INSERT INTO user_tb(username, password, email, created_at) VALUES (?, ?, ?,now())");
        query.setParameter(1, username);
        query.setParameter(2, password);
        query.setParameter(3, email);
        query.executeUpdate();
    }

    public User findByUsername(String username) {
        Query query = em.createNativeQuery("SELECT * FROM user_tb WHERE username = ?", User.class);
        query.setParameter(1, username);
        User user = (User) query.getSingleResult();
        try {
            return user;
        } catch (Exception e) {
            return null;
        }

    }
}
