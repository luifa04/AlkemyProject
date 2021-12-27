package com.alkemy.ong.common;

import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import java.time.LocalDateTime;

public class BaseGeneralTest {
    protected TestRestTemplate testRestTemplate = new TestRestTemplate();
    protected HttpHeaders headers = new HttpHeaders();

    @MockBean
    protected UserRepository userRepository;


    @LocalServerPort
    private int PORT;

    protected String createURLWithPort(String uri) {
        return "http://localhost:" + PORT + uri;
    }

    protected void login(String role) {
        String email = "david.mark@hotmail.com";
        String jwt = SecurityConfigTest.generateToken(email, role);
        headers.set("Authorization", jwt);
    }

    protected User generateUser(String role) {
        User user = new User();
        user.setUserId(1L);
        user.setFirstName("David");
        user.setLastName("Mark");
        user.setEmail("david.mark@hotmail.com");
        user.setPassword("123456");
        user.setPhoto("https://somosmas.jpg");
        user.setRole(generateRole(role));
        user.setEnabled(Boolean.TRUE);
        user.setDateCreation(LocalDateTime.now());
        user.setToken(null);
        return user;
    }

    protected Role generateRole(String name) {
        Role role = new Role();
        role.setIdRole(1L);
        role.setName(name);
        return role;
    }





}
