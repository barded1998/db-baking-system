package db7.bankingsystem.service;

import db7.bankingsystem.domain.User;
import db7.bankingsystem.domain.UserId;
import db7.bankingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private static long sequence;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        sequence = userRepository.findAll().size();
    }

    public UserDto save(UserDto userDto) {
        User user = new User();
        user.setId(new UserId(++sequence, userDto.getName(), userDto.getEmail(), userDto.getPhoneNumber()));
        user.setAddress(userDto.getAddress());
        user.setBirthday(userDto.getBirthday());
        user.setJob(userDto.getJob());
        userRepository.save(user);
        userDto.setUserId(sequence);
        return userDto;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(user -> {
            UserDto userDto = new UserDto();
            userDto.setUserId(user.getId().getUserId());
            userDto.setName(user.getId().getName());
            userDto.setEmail(user.getId().getEmail());
            userDto.setPhoneNumber(user.getId().getPhoneNumber());
            userDto.setAddress(user.getAddress());
            userDto.setBirthday(user.getBirthday());
            userDto.setJob(user.getJob());
            return userDto;
        }).collect(Collectors.toList());
    }

    public Optional<UserDto> findByUserId(long id) {
        User user = userRepository.findByIdUserId(id).get();
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId().getUserId());
        userDto.setName(user.getId().getName());
        userDto.setEmail(user.getId().getEmail());
        userDto.setPhoneNumber(user.getId().getPhoneNumber());
        userDto.setAddress(user.getAddress());
        userDto.setBirthday(user.getBirthday());
        userDto.setJob(user.getJob());
        return Optional.ofNullable(userDto);
    }
}
