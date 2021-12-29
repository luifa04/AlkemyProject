package com.alkemy.ong.integration.contact;

import static org.mockito.ArgumentMatchers.isA;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.alkemy.ong.common.BaseContactTest;
import com.alkemy.ong.dto.ContactRequestDto;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.security.RoleEnum;

public class ContactCreateGeneralTest extends BaseContactTest {
	
	
	 private ContactRepository contactRepository;
	 private ContactRequestDto contactRequest;
	 private RoleEnum roleEnum;
	
	 
    @BeforeEach
    Contact createContact(){

        Contact contact = generateContact();
        Mockito.when(contactRepository.save(isA(Contact.class))).thenReturn(generateContact());
        return contact;
    }

    @Test
    public void CreateContactSuccess() {

        login(roleEnum.getRoleName());

        contactRequest.setName("contactTest2");
        contactRequest.setEmail("prueb2a@gmail.com");
        contactRequest.setMessage("mensaje de prueba2");

        Mockito.when(contactRepository.save(isA(Contact.class))).thenReturn(generateContact());

        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort("/contacts"), HttpMethod.POST, new HttpEntity<>(contactRequest, headers), ContactRequestDto.class); 
        
        
    }
   
                	
    @Test
    public void CreateContactFailedBecauseName() {

        Contact contact = createContact();
        contact.setId(1L);
        contact.setName("");
        contact.setEmail("https://somosmas.jpg");
        contact.setMessage("Mensaje de prueba");

        Mockito.when(contactRepository.save(isA(Contact.class))).thenReturn(generateContact());

        login(RoleEnum.ADMIN.getRoleName());

        ContactRequestDto contactRequest = exampleContactRequest(); //por qué esta linea?
        
    }
    
    @Test
    public void CreateActivityFailedBecauseMessage() {
    	
    	 Contact contact = createContact();
         contact.setId(1L);
         contact.setName("Contact");
         contact.setEmail("carlos@gmail.com");
         contact.setMessage("Mensaje de prueba");

         login(RoleEnum.ADMIN.getRoleName());

         ContactRequestDto contactRequest = exampleContactRequest(); 
    	
    }
    
    
    
    @Test
    public void CreateContactFailedBecauseEmail() {

        Contact contact = createContact();
        contact.setId(1L);
        contact.setName("Contact");
        contact.setEmail("carlos@gmail.com");
        contact.setMessage("Mensaje de prueba");

        login(RoleEnum.ADMIN.getRoleName());

        ContactRequestDto contactRequest = exampleContactRequest();
}
}
