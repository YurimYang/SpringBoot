package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest //Spring Container와 Test 함께 실행
@Transactional
    //AutoCommit Mode : Insert call 을 하고 commit 하기 전엔 DB에 저장 X
    //Test 를 실행시, Transaction을 먼저하기 때문에 DB에 넣었던 데이터를 저장했다가 마지막에 rollback해줌


class MemberServiceintegrationTest {
    //test는 한글로 바꿔도 가능 .

    @Autowired  MemberService memberService;
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //new로 다른 객체를 같고 올 경우 다른 인스턴스이기 때문에 문제가 발생할 수 있음
    //MemoryMemberRepository의 new랑 service의 memorymemberRepository랑 다른 레포지토리임
    @Autowired MemberRepository memberRepository;




    //given, when, then 구조로 나눠서 test하기
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello2");

        //when
        Long saveId = memberService.join(member);

        //then
        Member FindMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(FindMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //memberService:join(member2)를 실행시, IllegalStateException 실행됨

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try{
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다.");
//        }catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//
//        }




        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}