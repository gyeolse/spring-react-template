package com.example.template.repository;

import com.example.template.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private  static long sequence = 0L; //0,1,2.... key값 생성해주는 애임


    //alt + ins 로 그대로 메서드 불러옴
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member); //store에 저장해줌.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //store에서 꺼내면 됨. 없으면 null이 나올텐데. 그럴땐 Optional.of를 씀
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //찾으면 걔를 반환함. 없으면 null
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //멤버들을 모두 반환해줌.
    }

    public void clearStore() {
        store.clear(); //모두 비워줌
    }
}
