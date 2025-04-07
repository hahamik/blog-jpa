package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

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
}
