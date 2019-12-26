package springfive.cms.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import springfive.cms.domain.models.Category;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {

}
