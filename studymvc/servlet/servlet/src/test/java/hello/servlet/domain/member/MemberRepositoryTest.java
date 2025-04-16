package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    // 테스트 끝나면 초기화
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    //본격적 테스트 시작
    @Test
    void save(){
        //given - 이런게 주어졌을 때
        Member member = new Member(); // alt + enter

        //when - 이런게 실행되었을 때
        Member saveMember = memberRepository.save(member);
        // MemberRepository의 save() 메서드가 제대로 동작하는지 검증하는 테스트 코드


        //then - 결과는 이거여야 해
//        saveMember.getId()로 id를 추출해서, 저장된 회원을 다시 조회합니다
        Member findMember = memberRepository.findById(saveMember.getId());

//        AssertJ를 통해 조회된 회원이 저장된 회원과 같은지 검사합니다 (동등성 확인)
        assertThat(findMember).isEqualTo(saveMember);

    }


    @Test
    void findAll(){
        // given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1,member2); //result안에 member1, member2가 있느냐

    }
}
