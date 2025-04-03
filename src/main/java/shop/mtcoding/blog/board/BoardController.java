package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

//    @GetMapping("/")
//    public String list() {
//        return "board/list";
//    }
//
//    @GetMapping("")
//    public String detail() {
//        return "board/detail";
//    }
//
//    @GetMapping
//    public String saveForm() {
//        return "board/save-form";
//    }
//
//    @GetMapping
//    public String updateForm() {
//        return "board/update-form";
//    }

}
