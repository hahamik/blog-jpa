package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;


    // 로그인 안했을때 // 무조건 4개
    // 로그인 했을때 ssar -> 5개
    // 로그인 했을때 ssar이 아니면 -> 4개
    // 그룹함수 -> Long으로 return
    public Long totalCount() {
        Query query = em.createQuery("select count(b) from Board b where b.isPublic = true", Long.class);
        return (Long) query.getSingleResult();
    }

    public Long totalCount(int usreId) {
        Query query = em.createQuery("select count(b) from Board b where b.isPublic = true or b.user.id = :userId", Long.class);
        query.setParameter("userId", usreId);
        return (Long) query.getSingleResult();
    }

    public Board findByIdJoinUserAndReplies(Integer id) {
        Query query = em.createQuery("select b from Board b join fetch b.user left join fetch b.replies r left join fetch r.user where b.id = :id order by r.id desc", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
    }

    public Board findByIdJoinUser(Integer id) {
        Query query = em.createQuery("select b from Board b join fetch b.user where b.id = :id", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
    }

    public Board findById(Integer id) {
        return em.find(Board.class, id);
    }
    // locahost:8080?page=0
    public List<Board> findAll(int page) {
        String sql = "select b from Board b where b.isPublic = true order by b.id desc";
        Query query = em.createQuery(sql, Board.class);
        query.setFirstResult(page * 3);
        query.setMaxResults(3);

        return query.getResultList();
    }

    public List<Board> findAll(Integer userId, int page) {
        String sql = "select b from Board b where b.isPublic = true or b.user.id = :userId order by b.id desc";
        Query query = em.createQuery(sql, Board.class);
        query.setParameter("userId", userId);
        query.setFirstResult(page * 3);
        query.setMaxResults(3);
        return query.getResultList();
    }
//    public List<Board> findAll(int page) {
//        String sql = "select b from Board b where b.isPublic = true order by b.id desc";
//        Query query = em.createQuery(sql);
//        query.setFirstResult(page*3);
//        query.setMaxResults(3);
//        return query.getResultList();
//    }
//
//    public List<Board> findAll() {
//        String sql = "select b from Board b where b.isPublic = true order by b.id desc";
//        Query query = em.createQuery(sql, Board.class);
//        return query.getResultList();
//    }

//    public List<Board> findAll(Integer userId) {
//        String sql = "select b from Board b where b.isPublic = true or b.user.id = :userId order by b.id desc";
//        Query query = em.createQuery(sql, Board.class);
//        query.setParameter("userId", userId);
//        return query.getResultList();
//    }

    public void save(Board board) {
        em.persist(board);
    }
}
