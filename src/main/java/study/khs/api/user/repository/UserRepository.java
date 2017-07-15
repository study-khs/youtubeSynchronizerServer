package study.khs.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.khs.api.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByUserTypeAndUserLoginId(Long userType, String userLoginId);
}
