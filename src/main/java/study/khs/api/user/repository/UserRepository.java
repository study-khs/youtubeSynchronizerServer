package study.khs.api.user.repository;

import org.springframework.data.repository.CrudRepository;

import study.khs.api.user.domain.User;

public interface UserRepository extends CrudRepository<User, String> {

}
