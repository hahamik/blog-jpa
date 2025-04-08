package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.love.Love;
import shop.mtcoding.blog.love.LoveRepository;
import shop.mtcoding.blog.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final LoveRepository loveRepository;

    @Transactional
    public void 글쓰기(BoardRequest.SaveDTO saveDTO, User sessionuser) {
        boardRepository.save(saveDTO.toEntity(sessionuser));
//                boardRepository.save(Board.builder()
//                .title(saveDTO.getTitle())
//                .content(saveDTO.getContent())
//                .isPublic(saveDTO.getIsPublic() == null ? true : false)
//                .user(sessionuser)
//                .build());
    }

    public List<Board> 글목록보기(Integer userId) {
        return boardRepository.findAll(userId);
    }

    public BoardResponse.DetailDTO 글상세보기(Integer boardId, Integer userId) {
        Board board = boardRepository.findByIdJoinUser(boardId);
        Love love = loveRepository.findByUserIdAndBoardId(userId, boardId);
        Boolean isLove = love != null ? true : false;
        int loveCount = loveRepository.findByBoardId(boardId);
        BoardResponse.DetailDTO detailDTO = new BoardResponse.DetailDTO(board, userId, isLove, loveCount);
        return detailDTO;
    }

    public Board 상세보기(int id) {
        return boardRepository.findById(id);
    }
}
