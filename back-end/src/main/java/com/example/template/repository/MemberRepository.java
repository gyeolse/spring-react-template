package com.example.template.repository;

import com.example.template.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //저장
    Optional<Member> findById(Long id); //가져오는데 null일 수 있는데, null을 반환하는 것 대신 optional로 반환하도록 요즘은 설정함.
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
