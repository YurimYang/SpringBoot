package com.example.hellospring.controller;

import com.example.hellospring.domain.Member;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController   {
    //Spring container 안에 Controller 객체가 생성되고 관리

    private final MemberService memberService;

    //@Autowired private MemberService memberService; (필드주입)

    //생성자주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //setter주입 (무조건 public으로 노출해야함)
//    @Autowired
//    public void setMemberService(MemberService memberService){
//        this.memberService = memberService;
//    }

    //회원등록 url작성
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());


        memberService.join(member);

        return "redirect:/"; //기존 페이지로 이동
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        //모든 member 다 조회함
        return "members/memberList";
    }


}
