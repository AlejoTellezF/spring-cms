package springfive.cms.domain.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfive.cms.domain.exceptions.CategoryNotFoundException;
import springfive.cms.domain.models.Category;
import springfive.cms.domain.repository.CategoryRepository;
import springfive.cms.domain.vo.CategoryRequest;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }


    public Flux<Category> findAll(){
        return this.categoryRepository.findAll();
    }

    public Mono<Category> findOne(String id) {
        return this.categoryRepository.findById(id);
    }

    public Mono<Category> create(CategoryRequest request){
        Category category = new Category();
        category.setName(request.getName());
        return this.categoryRepository.save(category);
    }

    public Mono<Category> update(String id, CategoryRequest request){
        return this.categoryRepository.findById(id).flatMap(categoryDatabase -> {
            categoryDatabase.setName(request.getName());
            return  this.categoryRepository.save(categoryDatabase);
        });
    }

    public void delete(String id){
        this.categoryRepository.deleteById(id);
    }


}
