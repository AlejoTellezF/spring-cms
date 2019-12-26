package springfive.cms.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;
import springfive.cms.domain.models.Category;

import java.util.UUID;

public class SimpleReactorExample {

    @Test
    public void coldBehavior(){
        Category sports = new Category();
        sports.setName("Sports");
        Category music = new Category();
        music.setName("music");
        Flux.just(sports,music).doOnNext(System.out::println);
    }

    @Test
    public void coldBehaviorWithSubscribe(){
        Category sports = new Category();
        sports.setId(UUID.randomUUID().toString());
        sports.setName("sports");
        Category music = new Category();
        music.setId(UUID.randomUUID().toString());
        music.setName("music");
        Flux.just(sports, music).doOnNext(System.out::println).subscribe();
    }

    @Test
    public void testHotPublisher(){
        UnicastProcessor<String> hotSource = UnicastProcessor.create();
        Flux<Category> hotPublisher = hotSource.publish().autoConnect().map((String t) -> Category.builder().name(t).build());
        hotPublisher.subscribe(category -> System.out.println("Subscriber 1: "+category.getName()));

        hotSource.onNext("sports");
        hotSource.onNext("cars");

        hotPublisher.subscribe(category -> System.out.println("Subscriber 2: "+category.getName()));
        hotSource.onNext("games");
        hotSource.onNext("electronics");

        hotSource.onComplete();
    }

}
