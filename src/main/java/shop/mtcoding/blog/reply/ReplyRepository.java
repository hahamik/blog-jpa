package shop.mtcoding.blog.reply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReplyRepository {

    private final EntityManager em;

    public Reply findById(Integer id) {
        return em.find(Reply.class, id);
    }

    public void delete(int id) {
        em.remove(id);
    }

    public List<Reply> findAllByBoardId(Integer boardId) {
        Query query = em.createQuery("SELECT r FROM Reply r left join fetch r.user WHERE r.board.id = :boardId", Reply.class);
        query.setParameter("boardId", boardId);
        List<Reply> replies = query.getResultList();
        return replies;
    }

    public void save(Reply reply) {
        em.persist(reply);
    }


}
