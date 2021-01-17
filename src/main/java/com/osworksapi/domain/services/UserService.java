package com.osworksapi.domain.services;

import com.osworksapi.domain.exceptions.BusinessException;
import com.osworksapi.domain.model.User;
import com.osworksapi.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) throws BusinessException {
        boolean existsEmail = userRepository.existsByEmail(user.getEmail());

        existsEmail(user.getEmail());

        return userRepository.save(user);
    }

    public User update(Long id, User user) throws BusinessException {
        existsEmail(user.getEmail());

        user.setId(id);

        return userRepository.save(user);
    }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private void existsEmail(String email) {
        boolean existsEmail = userRepository.existsByEmail(email);

        if (existsEmail) {
            throw new BusinessException("Email já existente em nossa base de dados");
        }
    }
}
