package com.alkemy.ong.integration.contact;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;

public class ContactCreateGeneralTest {
	
	 private ContactRepository contactRepository;
	
    @BeforeEach
    void createContact(){

        //Contact contact = generateContact();
       // Mockito.when(contactRepository.save(isA(Activity.class))).thenReturn(generateActivity());
    }

  /*  @Test
    public void CreateContactSuccess() {

        login(RoleEnum.ADMIN.getRoleName());

        contactRequest.setName("contactTest2");
        contactRequest.setEmail("prueb2a@gmail.com");
        contactRequest.setMessage("mensaje de prueba2");

        Mockito.when(contactRepository.save(isA(Contact.class))).thenReturn(generateContact());

        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort("/contacts"), HttpMethod.POST, new HttpEntity<>(activityRequest, headers), ActivityRequest.class); public void CreateActivitySuccess() {
   
                	
    @Test
    public void CreateContactFailedBecauseName() {

        Activity activity = generateActivity();
        activity.setId(1L);
        activity.setName("New Activity");
        activity.setImage("https://somosmas.jpg");
        activity.setContent("New Content");

        Mockito.when(activityRepository.save(isA(Activity.class))).thenReturn(generateActivity());

        login(RoleEnum.ADMIN.getRoleName());

        ActivityRequest activityRequest = exampleActivityRequest();
        
    public void CreateActivityFailedBecauseMessage() {
    	
    	
    }
    
    
    
    @Test
    public void CreateActivityFailedBecauseEmail() {

        Mockito.when(activityRepository.save(isA(Activity.class))).thenReturn(generateActivity());

        Activity activity = generateActivity();
        activity.setId(1L);
        activity.setName("Activity");
        activity.setImage("https://activity.jpg");
        activity.setContent("New Content");

        login(RoleEnum.ADMIN.getRoleName());

        ActivityRequest activityRequest = exampleActivityRequest();*/
}
