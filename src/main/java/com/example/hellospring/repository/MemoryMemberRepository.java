package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence); //저장할때, id값 한칸씩 올려서 저장하기
        store.put(member.getId(), member); //HashMap에 저장하기
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //Null일 경우를 감안해서 감싸서 반환하기 (클라이언트딴에서 제작)
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //이름과 같은 멤버 찾기 필터링
                .findAny(); //찾으면 반환하기 (하나라도 찾기)
        //루프를 돌면서 하나 찾아지면 바로 반환값이 전달됨
        //만약 null이면 null값이 반환됨
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //자바 실무는 리스트로 많이 사용함
    }

    public void clearStore() {
        store.clear();
    }
    //저장된 데이터 모두 지우기
}
