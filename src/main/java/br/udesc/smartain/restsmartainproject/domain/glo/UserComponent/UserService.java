package br.udesc.smartain.restsmartainproject.domain.glo.UserComponent;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<List<User>> findAllUsers(){
        return userRepository.findAllUsers();
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(long id){
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByLoginAndSenha(String login, String senha){
        return userRepository.findUserByLoginAndSenha(login, senha);
    }
    
    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional
    public void saveAll(@Valid List<User> users){
        userRepository.saveAll(users);
    }

}
