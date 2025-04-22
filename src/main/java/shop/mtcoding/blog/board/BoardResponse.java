package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.reply.Reply;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BoardResponse {

    @Data
    public static class DTO {
        private List<Board> boards;
        private Integer prev;
        private Integer next;
        private Integer size;
        private Integer totalCount;
        private Integer totalPage;
        private Integer current;
        private Boolean isLast; // totalCount, size=3, totalPage ==current
        private Boolean isFirst;
        private List<Integer> numbers;
        private List<Integer> pNumbers;
        private Integer pageSize;

        public DTO(List<Board> boards,Integer current,Integer totalCount) {
            this.boards = boards;
            this.prev = current -1;
            this.next = current +1;
            this.size=3;//size 어차피 고정 (페이지 글 숫자) 그래서 보통은 final로 따로빼서 사용
            this.totalCount = totalCount; // 5개를 given으로 둬서 먼저 짜고
            this.pageSize = 5;
            this.current = current;


            this.totalPage = makeTotalPage(totalCount,size);
            this.numbers = pageNumbers(current,pageSize);
            this.isFirst = current == 0;
            this.isLast  = (totalPage-1)==current;
        }

        private Integer makeTotalPage(int totalCount, int size) {
            int rest = totalCount % size > 0 ? 1 : 0;
            return totalCount / size + rest;
        }



        private List<Integer> pageNumbers(int current,int pageSize){
            List<Integer> numbers = new ArrayList<>();
            // 페이지 크기가 5여서 음.... 4페이지 까지 밖에 안나오는데?
            // i=0 이 바껴야될듯? 근데 뭘로 바껴야하지?
            int firstPage = current/pageSize*pageSize;
            System.out.println("----------------------");
            System.out.println(firstPage);
            System.out.println("----------------------");
//            int lastPage = (current/pageSize+1)*pageSize;  // 이건 아닌듯... 10페이지까지 나옴 gma..
            int lastPage = (current/pageSize+1)*pageSize>totalPage?totalPage:(current/pageSize+1)*pageSize; // 된 거 같은데??? 캬
            System.out.println("----------------------");
            System.out.println(lastPage);
            System.out.println("----------------------");
            for (int i=firstPage;i<lastPage;i++){
                numbers.add(i);
            }
            return numbers;
        }
    }

    // 상세보기 화면에 필요한 데이터
    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Boolean isPublic;
        private Boolean isOwner;
        private Boolean isLove;
        private Integer loveCount;
        private String username;
        private Timestamp createdAt;
        private Integer loveId;

        private List<ReplyDTO> replies;

        @Data
        public class ReplyDTO {
            private Integer id;
            private String content;
            private String username;
            private Boolean isOwner;

            public ReplyDTO(Reply reply, Integer sessionUserId) {
                this.id = reply.getId();
                this.content = reply.getContent();
                this.username = reply.getUser().getUsername();
                this.isOwner = reply.getUser().getId().equals(sessionUserId);
            }
        }

        public DetailDTO(Board board, Integer sessionUserId, Boolean isLove, Integer loveCount, Integer loveId) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.isPublic = board.getIsPublic();
            this.isOwner = sessionUserId == board.getUser().getId();
            this.username = board.getUser().getUsername();
            this.createdAt = board.getCreatedAt();
            this.isLove = isLove;
            this.loveCount = loveCount;
            this.loveId = loveId;

            List<ReplyDTO> repliesDTO = new ArrayList<>();

            for (Reply reply : board.getReplies()) {
                ReplyDTO replyDTO = new ReplyDTO(reply, sessionUserId);
                repliesDTO.add(replyDTO);
            }

            this.replies = repliesDTO;
        }
    }

}
