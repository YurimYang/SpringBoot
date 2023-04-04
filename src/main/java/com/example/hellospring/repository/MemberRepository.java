package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;

import java.util.Optional;
import java.util.List;

public interface MemberRepository {
    Member save(Member member); //회원정보가 저장이 됨
    Optional<Member> findById(Long id); //Id찾기
    Optional<Member> findByName(String name); //이름찾기
    //Optional = findById, findByName이 null일 경우를 대비해,Optional으로 감싸서 반환하기
    List<Member> findAll(); //전체찾기
}
