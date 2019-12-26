package springfive.cms.domain.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import springfive.cms.domain.models.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
