package springfive.cms.domain.service;

import org.springframework.stereotype.Service;
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

    public User findOne(String id){
        final Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else{
            throw new UserNotFoundException(id);
        }
    }

    public Iterable<User> findAll(){
        return this.userRepository.findAll();
    }

    public User create(UserRequest userRequest){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setIdentity(userRequest.getIdentity());
        user.setName(userRequest.getName());
        user.setRole(userRequest.getRole());
        return this.userRepository.save(user);
    }

    public User update(String id, UserRequest userRequest){
        final Optional<User> optionalUser = this.userRepository.findById(id);
        if(optionalUser.isPresent()){
            final User user = optionalUser.get();
            user.setIdentity(userRequest.getIdentity());
            user.setName(userRequest.getName());
            user.setRole(userRequest.getRole());
            return this.userRepository.save(user);
        }
        else{
            throw new UserNotFoundException(id);
        }
    }

    public void delete(String id){
        final Optional<User> optionalUser = this.userRepository.findById(id);
        optionalUser.ifPresent(this.userRepository::delete);
    }
}
