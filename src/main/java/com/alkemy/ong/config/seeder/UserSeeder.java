
package com.alkemy.ong.config.seeder;

import com.alkemy.ong.exception.EmailExistException;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.alkemy.ong.security.RoleEnum;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@AllArgsConstructor
public class UserSeeder implements CommandLineRunner{
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    
    @Override
    public void run(String... args){
        loadUsers();
    }
    
    private void loadUsers(){
        if (userRepository.count() == 0) {
            createUsers();
        }
    }
    
    
    private void createUsers(){
        Integer k=1;
        for (int i = 1; i < 21; i++) {
            if (i<= 10) {
                userRepository.save(buildUserAdmin("useradmin"+i,"useradmin"+i,"useradmin"+i+"@gmail.com","useradmin"+i));
            }else{
               userRepository.save(buildUser("useruser"+k,"useruser"+k,"useruser"+k+"@gmail.com","useruser"+k));
               k++;
            }
        }     
    }
    
    private Role createRoleUser(){
        Role roleUser= new Role();
        roleUser.setDescription("USER");
        roleUser.setName("USER");
        roleUser.setRoleEnum(RoleEnum.USER);
        roleUser.setTimestamp(new Date());
        return roleUser;
    }
    
    private Role createRoleAdmin(){
        Role roleAdmin= new Role();
        roleAdmin.setDescription("ADMIN");
        roleAdmin.setName("ADMIN");
        roleAdmin.setRoleEnum(RoleEnum.ADMIN);
        roleAdmin.setTimestamp(new Date());
        return roleAdmin;
    }
    
    private User buildUser(String name, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoto(null);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(createRoleUser());
        user.setDateCreation(LocalDateTime.now());
        return user; 
    }
    
    private User buildUserAdmin(String name, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoto(null);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(createRoleAdmin());
        user.setDateCreation(LocalDateTime.now());
        return user; 
    }
    
}
