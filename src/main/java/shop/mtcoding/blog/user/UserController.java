package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/user/update-form")
    public String updateForm() {
        return "user/update-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @GetMapping("/join-form")
    public String joinForm(UserRequest.UserJoinDTO userJoinDTO) {
        return "user/join-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.UserJoinDTO userJoinDTO) {
        userService.회원가입(userJoinDTO);
        return "redirect:/login-form";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
