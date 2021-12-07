package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserUpdateDto;
import com.alkemy.ong.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserRequest;
import com.alkemy.ong.exception.EmailExistException;
import com.alkemy.ong.model.User;
import org.springframework.context.MessageSource;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.security.RoleEnum;
import com.alkemy.ong.service.IUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    @Override
    public User createUser(UserRequest userRequest) throws EmailExistException {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new EmailExistException(userRequest.getEmail());
        }
        return userRepository.save(generateUser(userRequest));
    }

    private User generateUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhoto(userRequest.getPhoto());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(roleRepository.findByName(RoleEnum.USER.getName()));
        user.setDateCreation(LocalDateTime.now());
        return user;
    }


    @Override
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

	@Override
	public void makeAdmin(String username) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public List<UserDto> getUsers() {

        List<UserDto> userDto = userRepository.findAll().stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
        return userDto;
    }

    private UserDto mapUserToUserDto(User user){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }

    private UserUpdateDto mapUserToUserUpdateDto(User user){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserUpdateDto.class);
    }

//    @Override
//    @Transactional
//    public void makeAdmin(String username){
//        userRepository.updateUserRole(username, roleRepository.findByName(RoleEnum.ADMIN.getName()));
//    }

    public UserUpdateDto update(Long id, UserUpdateDto userUpdateDto){
        Optional<User> entity = userRepository.findById(id);
        String messageError = messageSource.getMessage("updateUserMessageTemplate.failure", null, Locale.US);
        if(!entity.isPresent()) {
            throw new NotFoundException(messageError);
        }
        userRefreshValues(entity.get(), userUpdateDto);
        User userSaved = userRepository.save(entity.get());
        UserUpdateDto result = mapUserToUserUpdateDto(userSaved);
        return result;

    }

    public void userRefreshValues(User user, UserUpdateDto userUpdateDto){
        if(userUpdateDto.getFirstName() != null){
            user.setFirstName(userUpdateDto.getFirstName());
        }
        if(userUpdateDto.getLastName() != null) {
            user.setLastName(userUpdateDto.getLastName());
        }
        if(userUpdateDto.getEmail()!= null){
            user.setEmail(userUpdateDto.getEmail());
        }
        if(userUpdateDto.getPhoto() != null){
            user.setPhoto(userUpdateDto.getPhoto());
        }
        if(userUpdateDto.getPassword() != null){
            user.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
        }
    }


}
