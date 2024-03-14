package com.example.template.member.api;

import com.example.template.member.domain.Member;
import com.example.template.member.repository.MemberRepository;
import com.example.template.member.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberApi {

    private final MemberRepository memberRepository;

    // 모든 사람이 접근 가능
    @GetMapping("home")
    public String home() {
        return "<h1>home</h1>";
    }

    // Tip : JWT를 사용하면 UserDetailsService를 호출하지 않기 때문에 @AuthenticationPrincipal 사용
    // 불가능.
    // 왜냐하면 @AuthenticationPrincipal은 UserDetailsService에서 리턴될 때 만들어지기 때문이다.

    // 유저 혹은 매니저 혹은 어드민이 접근 가능
    @GetMapping("user")
    public String user(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("principal : " + principal.getMember().getId());
        System.out.println("principal : " + principal.getMember().getUsername());
        System.out.println("principal : " + principal.getMember().getPassword());

        return "<h1>user</h1>";
    }

    // 매니저 혹은 어드민이 접근 가능
    @GetMapping("manager/reports")
    public String reports() {
        return "<h1>reports</h1>";
    }

    // 어드민이 접근 가능
    @GetMapping("admin/users")
    public List<Member> users() {
        return memberRepository.findAll();
    }

    @PostMapping("join")
    public String join(@RequestBody Member member) {
        Member.builder()
                .password(member.getPassword())
                .role("ROLE_USER")
                .build();

        memberRepository.save(member);
        return "회원가입완료";
    }

}
