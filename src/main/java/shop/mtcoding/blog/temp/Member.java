package shop.mtcoding.blog.temp;

public class Member {
    private int id;
    private String name;
    private String password;


    // 생성자 오버로딩도 많이 안해도 되고
    // 생성자 신경안써도 되고
    // 순서도 신경 안써도 되고 편하게 ㅅ르 수 있음
    public static Member builder() {
        return new Member();
    }

    public Member id(int id) {
        this.id = id;
        return this;
    }

    public Member name(String name) {
        this.name = name;
        return this;
    }

    public Member password(String password) {
        this.password = password;
        return this;
    }

    public static void main(String[] args) {

        Member m = Member.builder()
                .id(1)
                .name("길동");

    }

}