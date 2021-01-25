package com.example.template.service;

import com.example.template.domain.Member;
import com.example.template.repository.MemberRepository;
import com.example.template.repository.MemoryMemberRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

// ctrl + shift + t : test case 작성 바로 가능
public class MemberService {
    private final MemberRepository memberRepository;
    //외부에서 넣어줄 수 있도록 생성자 만들기
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {

        //#1. 이 내용을 줄여서
        //같은 이름이 있는 중복 회원은 안됨.
//        Optional<Member> result =  memberRepository.findByName(member.getName());
//        //optional 안의 함수. ifPresent. 값이 있으면, 예외처리
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        //#2. 이렇게 변경이 가능. refactor 해서 extract method
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다. ");
                });
    }

    //최대한 비즈니스에 가깝게 설계하는 것이 좋음.
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
