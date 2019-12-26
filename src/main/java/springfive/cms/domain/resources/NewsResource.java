package springfive.cms.domain.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfive.cms.domain.models.News;
import springfive.cms.domain.models.Review;
import springfive.cms.domain.service.NewsService;
import springfive.cms.domain.vo.NewsRequest;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsResource {

    private final NewsService newsService;

    public NewsResource(NewsService newsService){
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<News>> findOne(@PathVariable("id") String id){
        return ResponseEntity.ok(this.newsService.findOne(id));
    }

    @GetMapping
    public ResponseEntity<Flux<News>> findAll(){
        return ResponseEntity.ok(this.newsService.findAll());
    }

    @PostMapping
    public ResponseEntity<Mono<News>> newNews(NewsRequest news){
        return new ResponseEntity<>(this.newsService.create(news), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<News>> updateNews(@PathVariable("id") String id, @RequestBody NewsRequest newsRequest){
        return new ResponseEntity<>(this.newsService.update(id, newsRequest),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeNews(@PathVariable("id") String id){
        this.newsService.delete(id);
    }

    @GetMapping(value = "/{id}/review/{userId}")
    public ResponseEntity<Review> review(@PathVariable("id") String id, @PathVariable("userId") String userId){
        return ResponseEntity.ok(new Review());
    }

    @GetMapping(value = "/revised")
    public ResponseEntity<List<News>> revisedNews(){
        return ResponseEntity.ok(Arrays.asList(new News(), new News()));
    }
}
