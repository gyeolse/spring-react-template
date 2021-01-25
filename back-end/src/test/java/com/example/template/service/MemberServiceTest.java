package com.example.template.service;

import com.example.template.domain.Member;
import com.example.template.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    //각 TestCase를 실행시키기 전에, 매번 실행이 될 것.
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    // clear source  => memberRepository 에서 들고옴
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given (이런 상황이 주어졌음)
        Member member = new Member();
        member.setName("Hello");

        //when ( 이럴 때 )
        Long saveId = memberService.join(member);

        //then ( 이게 나와야 함 )
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //예외처리 로직 확인
    @Test
    public void 중복회원에외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //이 로직일 때 예외가 터져야 한다!
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. ");
//
//        //예외를 잡을 방법?
//        try {
//            memberService.join(member2); //error가 나와야함. 예외가 터져야함.
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. ");
//        }
//        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}