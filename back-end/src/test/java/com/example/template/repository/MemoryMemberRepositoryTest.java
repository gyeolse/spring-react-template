package com.example.template.repository;

import com.example.template.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //data clear를 하는 과정이 필요함. test 끝날 때마다, repository를 지워줘야함.
    //한번의 test case가 끝날때마다 자동으로 실행되는 함수.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get(); //optional에서 꺼낼 때는 get으로 꺼내기 가능

        //똑같으면, True일 것.
        //System.out.println("result = " + (result == member));

        //방법 1. 대신 사용하는 기능 (기댓값, 실제값)
        //Assertions.assertEquals(member,result);

        //방법 2. member가 result와 같냐? 가독성이 좋음.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //findbyname 잘 동작하는지 확인
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
