package shop.mtcoding.blog.user;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.blog._core.Resp;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        userService.회원가입(joinDTO);
        return "redirect:/login-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO, HttpServletResponse response) {
        User sessionUser = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", sessionUser);

        // 기존 rememberMe 쿠키 삭제 (체크 안 했을 경우)
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0); // 즉시 만료 //
//        cookie.setPath("/"); // 애플리케이션 전체에서 삭제 // /login 페이지에서쿠키를 생성하면 다른 페이지에서는 해당 쿠키를 찾지 못함(/login) 해당 경로에서만 쿠키를 사용할 수 있음. 삭제하려는
        response.addCookie(cookie);

        // rememberMe 체크 시 쿠키 저장
        if (loginDTO.getRememberMe() != null) {
            cookie = new Cookie("username", loginDTO.getUsername());
            cookie.setMaxAge(7 * 24 * 60 * 60); // 7일 유지
            response.addCookie(cookie);
        }

        return "redirect:/";
    }
//            cookie.setMaxAge(7 * 24 * 60 * 60);


    @GetMapping("logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/check-username-available/{username}")
    public @ResponseBody Resp<?> checkUsernameAvailable(@PathVariable("username") String username) {
        Map<String, Object> dto = userService.유저네임중복체크(username);
        return Resp.ok(dto);
    }

}