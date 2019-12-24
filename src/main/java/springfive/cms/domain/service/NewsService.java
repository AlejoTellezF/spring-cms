package springfive.cms.domain.service;

import org.springframework.stereotype.Service;
import springfive.cms.domain.exceptions.NewsNotFoundException;
import springfive.cms.domain.models.*;
import springfive.cms.domain.repository.NewsRepository;
import springfive.cms.domain.vo.NewsRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    public News findOne(String id){
        final Optional<News> optionalNews = this.newsRepository.findById(id);
        if(optionalNews.isPresent())
            return optionalNews.get();
        else
            throw new NewsNotFoundException(id);
    }

    public List<News> findAll(){
        return this.newsRepository.findAll();
    }

    public News create(NewsRequest request){
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

    public News update(String id, NewsRequest request){
        final Optional<News> optionalNews = this.newsRepository.findById(id);
        if(optionalNews.isPresent()) {
            News news = optionalNews.get();
            news.setTitle(request.getTitle());
            news.setContent(request.getContent());
            news.setCategories(request.getCategories());
            news.setTags(request.getTags());
            //author?
            //mandatoryReviewers?
            //reviewers?
            return this.newsRepository.save(news);
        }else{
            throw new NewsNotFoundException(id);
        }
    }

    public void delete(String id){
        final Optional<News> optionalNews = this.newsRepository.findById(id);
        optionalNews.ifPresent(this.newsRepository::delete);
    }



}
