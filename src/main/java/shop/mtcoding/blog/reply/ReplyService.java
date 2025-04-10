package shop.mtcoding.blog.reply;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardRepository;
import shop.mtcoding.blog.user.User;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void 댓글쓰기(ReplyRequest.SaveDTO saveDTO, User sessionUser) {
        // 존재하는 게시글?
        Board board = boardRepository.findById(saveDTO.getBoardId());
        if (board == null) throw new RuntimeException("게시글을 찾을 수 없습니다.");
        // 댓글 쓰는 사람이 본인이 맞나?? 아니면 펑
//        if (!replyDTO.getUserId().equals(sessionUser.getId())) {
//            throw new RuntimeException("올바른 접근이 아닙니다.");
//        }
        // insert
        Reply reply = saveDTO.toEntity(sessionUser);
        replyRepository.save(reply);
    }

    @Transactional
    public Integer 댓글삭제(Integer id, User sessionUser) {


        // 존재하는 댓글??=
        Reply reply = replyRepository.findById(id);
        if (reply == null) throw new RuntimeException("댓글을 찾을 수 없습니다.");
        // 본인 댓글이 맞나??
        if (!reply.getUser().getId().equals(sessionUser.getId())) throw new RuntimeException("해당 댓글에 대한 권한이 없습니다.");
        Integer boardId = reply.getBoard().getId();
        System.out.println("삭제 전 boardId" + boardId);

        // 존재하는 게시글?
        Board board = boardRepository.findById(boardId);
        if (board == null) throw new RuntimeException("게시글을 찾을 수 없습니다.");

        // 댓글삭제
        replyRepository.deleteById(id);
        System.out.println("삭제 후 boardId" + boardId);
        return boardId;
    }


}
