package springfive.cms.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfive.cms.domain.exceptions.CategoryNotFoundException;
import springfive.cms.domain.models.Category;
import springfive.cms.domain.repository.CategoryRepository;
import springfive.cms.domain.vo.CategoryRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }


    public List<Category> findAll(){
        return this.categoryRepository.findAll();
    }

    public List<Category> findByNameStartingWith(String name) {
        return this.categoryRepository.findByNameIgnoreCaseStartingWith(name);
    }

    public Category findOne(String id) {
        final Optional<Category> category = this.categoryRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new CategoryNotFoundException(id);
        }
    }

    public List<Category> findByName(String name){
        return this.categoryRepository.findByName(name);
    }

    @Transactional
    public Category create(CategoryRequest request){
        Category category = new Category();
        category.setName(request.getName());
        return this.categoryRepository.save(category);
    }

    @Transactional
    public Category update(String id, CategoryRequest request){
        Category category = this.categoryRepository.findById(id).get();
        category.setName(request.getName());
        return this.categoryRepository.save(category);
    }

    @Transactional
    public void delete(String id){
        final Optional<Category> category = this.categoryRepository.findById(id);
        category.ifPresent(this.categoryRepository::delete);
    }


}
