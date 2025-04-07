package shop.mtcoding.blog.board.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardRepository;

import java.util.List;

@Import(BoardRepository.class)// BoardRepository
@DataJpaTest // EntityManger, PC
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findAll_test() {
        // given
        Integer userId = 1;
        // when
        List<Board> boardList = boardRepository.findAll(userId);

        // Lazy -> Board -> User(id=1)
        // Eager -> N+1  -> Board조회 -> 연관된 User 유저 수 만큼 주회
//        // Eager -> Join -> 한방쿼리
//        System.out.println("--------------------");
//        boardList.get(0).getUser().getUsername();
//        System.out.println("--------------------");
        // eye
        for (Board board : boardList) {
            System.out.print(board.getId());
            System.out.print(board.getTitle());
            System.out.println();
        }
    }

    @Test
    public void findByIdJoinUserTest() {
        Integer id = 1;
        Board board = boardRepository.findByIdJoinUser(id);
    }
}
