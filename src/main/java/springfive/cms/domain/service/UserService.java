package springfive.cms.domain.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfive.cms.domain.exceptions.UserNotFoundException;
import springfive.cms.domain.models.User;
import springfive.cms.domain.repository.UserRepository;
import springfive.cms.domain.vo.UserRequest;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Mono<User> findOne(String id){
        return this.userRepository.findById(id);
    }

    public Flux<User> findAll(){
        return this.userRepository.findAll();
    }

    public Mono<User> create(UserRequest userRequest){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setIdentity(userRequest.getIdentity());
        user.setName(userRequest.getName());
        user.setRole(userRequest.getRole());
        return this.userRepository.save(user);
    }

    public Mono<User> update(String id, UserRequest userRequest){
        return this.userRepository.findById(id).flatMap(userDatabase -> {
            userDatabase.setIdentity(userRequest.getIdentity());
            userDatabase.setName(userRequest.getName());
            userDatabase.setRole(userRequest.getRole());
            return this.userRepository.save(userDatabase);
        });
    }

    public void delete(String id){
        this.userRepository.deleteById(id);
    }
}
