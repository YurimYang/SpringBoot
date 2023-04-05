package com.example.hellospring.controller;

import com.example.hellospring.domain.Member;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
