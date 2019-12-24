package springfive.cms.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import springfive.cms.domain.models.News;

@Component
public interface NewsRepository extends MongoRepository<News, String> {
}
