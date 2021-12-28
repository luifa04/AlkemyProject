package com.alkemy.ong.common;

import com.alkemy.ong.dto.ContactRequestDto;
import com.alkemy.ong.model.Contact;
import java.util.ArrayList;
import java.util.List;

public class BaseContactTest extends BaseGeneralTest{

    protected ContactRequestDto exampleContactRequest() {
    	ContactRequestDto contactRequestDto = new ContactRequestDto();
    	contactRequestDto.setName("Carlos");
    	contactRequestDto.setEmail("Carlos@hotmail.com");
    	contactRequestDto.setMessage("mensaje de prueba");
        return contactRequestDto;
    }

    protected List<Contact> generateContacts(int count){
        List<Contact> contacts = new ArrayList<>();
        for(int i=0; i<=count;i++){
            contacts.add(generateContact());
        }
        return contacts;
       
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

