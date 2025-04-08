package shop.mtcoding.blog.love;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LoveRepository {

    private final EntityManager em;

    public Love findByUserIdAndBoardId(int userId, int boardId) {
        Query query = em.createQuery("SELECT lo FROM Love lo WHERE lo.user.id = :userId AND lo.board.id = :boardId");
        query.setParameter("userId", userId);
        query.setParameter("boardId", boardId);
        try {
            return (Love) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public int findByBoardId(int boardId) {
        Query query = em.createQuery("select count(lo) from Love lo where lo.board.id = :boardId");
        query.setParameter("boardId", 5);

        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public Integer findByBoardId1(int boardId) {
        Query query = em.createQuery("SELECT COUNT(lo) FROM Love lo WHERE lo.board.id = :board_id", Love.class);
        query.setParameter("board_id", boardId);
        List<Love> loves = query.getResultList();
        return loves.size();
    }
}
