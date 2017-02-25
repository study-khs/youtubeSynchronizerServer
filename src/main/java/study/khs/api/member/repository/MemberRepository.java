package study.khs.api.member.repository;

import org.springframework.data.repository.CrudRepository;

import study.khs.api.member.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {

}
