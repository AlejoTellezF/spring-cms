package springfive.cms.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import springfive.cms.domain.models.News;

@Component
public interface NewsRepository extends JpaRepository<News, String> {
}
