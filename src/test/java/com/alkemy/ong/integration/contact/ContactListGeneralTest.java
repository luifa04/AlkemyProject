package com.alkemy.ong.integration.contact;

import com.alkemy.ong.common.BaseUserTest;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.security.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactListGeneralTest extends BaseUserTest {
    private final String PATH = "/contacts/";
    private ContactRepository contactRepository;

    @Test
    public void ReturnUnauthorizedIfUserIsNotAdmin() {
        login(RoleEnum.USER.getRoleName());
        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.GET,
                new HttpEntity<>(headers), Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @SuppressWarnings("rawtypes") //ver qué onda
	@Test
    public void ListContactSuccess() {
        @SuppressWarnings("unchecked")
		List<Contact> contacts = (List<Contact>) generateContact();
        when(contactRepository.findAll()).thenReturn(contacts);

        login(RoleEnum.ADMIN.getRoleName());

        ResponseEntity<List> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.GET,
                new HttpEntity<>(headers),
               List.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
    protected Contact generateContact() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setEmail("marta.sanchez@hotmail.com");
        contact.setMessage("mensaje de prueba");
        contact.setEnabled(Boolean.TRUE);
      
        return contact;
    }

}
