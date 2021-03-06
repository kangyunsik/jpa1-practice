package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        // GIVEN
        Member member = new Member();
        member.setName("kim");

        // WHEN
        memberService.join(member);
        
        // THEN
        assertEquals(member, memberRepository.findById(member.getId()).get());
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        // GIVEN
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // WHEN
        memberService.join(member1);
        
        // THEN
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}