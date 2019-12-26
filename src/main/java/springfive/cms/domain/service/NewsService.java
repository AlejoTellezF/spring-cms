package springfive.cms.domain.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfive.cms.domain.exceptions.NewsNotFoundException;
import springfive.cms.domain.models.*;
import springfive.cms.domain.repository.NewsRepository;
import springfive.cms.domain.vo.NewsRequest;

import java.util.UUID;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    public Mono<News> findOne(String id){
        return this.newsRepository.findById(id);
    }

    public Flux<News> findAll(){
        return this.newsRepository.findAll();
    }

    public Mono<News> create(NewsRequest request){
        News news = new News();
        news.setId(UUID.randomUUID().toString());
        news.setTitle(request.getTitle());
        news.setContent(request.getContent());
        news.setCategories(request.getCategories());
        news.setTags(request.getTags());
        //author?
        //mandatoryReviewers?
        //reviewers?
        return this.newsRepository.save(news);
    }

    public Mono<News> update(String id, NewsRequest request){
        return this.newsRepository.findById(id).flatMap(newsDatabase ->{
            newsDatabase.setTitle(request.getTitle());
            newsDatabase.setContent(request.getContent());
            newsDatabase.setCategories(request.getCategories());
            newsDatabase.setTags(request.getTags());
            return this.newsRepository.save(newsDatabase);
        });
    }

    public void delete(String id){
        this.newsRepository.deleteById(id);
    }



}
