package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class JspMemberController {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/jsp/members/new-form")
    public String newForm() {
        return "member/new-form";
    }

    @PostMapping("/jsp/members/save")
    public String save(@RequestParam String username,
                       @RequestParam int age,
                       Model model) {
        Member member = new Member(username, age);
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "member/save-result";
    }

    @GetMapping("/jsp/members")
    public String list(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "member/member";
    }
}
