package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;


    public void save(Board board) {
        em.persist(board);
    }

    // 이렇게 해결해도 되고 오버로딩으로 해결해도 되는데 오버로딩이 더 나은듯?
    public List<Board> findAll(Integer userId) {
        String s1 = "select b from Board b where b.isPublic = true or b.user.id = :userId order by b.id desc";
        String s2 = "select b from Board b where b.isPublic = true order by b.id desc";

        Query query = null;
        if (userId == null) {
            query = em.createQuery(s2, Board.class);
        } else {
            query = em.createQuery(s1, Board.class);
            query.setParameter("userId", userId);
        }

        return query.getResultList();
    }

    public Board findById(Integer id) {
        return em.find(Board.class, id);// 쿼리짜도 되는데 얘를 써야 캐싱을 함
    }

    public Board findByIdJoinUser(Integer id) {
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class); // on절이 있는데 생략 가능하다. 그냥 join이 -> innerjoin , left join 이 left outer join
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
    }
}
