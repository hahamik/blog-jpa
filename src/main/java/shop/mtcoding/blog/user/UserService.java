package shop.mtcoding.blog.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.UserJoinDTO userJoinDTO) {
        userRepository.join(userJoinDTO.getUsername(), userJoinDTO.getPassword(), userJoinDTO.getEmail());
    }

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        // 해당 아이디가 실제로 존재하나?
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (user == null) throw new RuntimeException("없는 아이디");
        // 비밀번호가 맞나?
        if (!(user.getPassword().equals(loginDTO.getPassword()))) throw new RuntimeException("비밀번호 틀림");
        // login 수행
        return user;
    }
}
