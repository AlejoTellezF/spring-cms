package springfive.cms.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springfive.cms.domain.models.User;

public interface UserRepository extends JpaRepository<User, String> {
}
