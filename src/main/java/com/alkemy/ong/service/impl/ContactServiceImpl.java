package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactRequestDto;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import lombok.AllArgsConstructor;

import com.alkemy.ong.service.IContactService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements IContactService{

	private final ContactRepository contactRepository;

	@Override
	public Contact addContact(ContactRequestDto contactRequestDto) throws Exception {
		Contact contact = new Contact();
		contact.setName(contactRequestDto.getName());
		contact.setEmail(contactRequestDto.getEmail());
		contact.setMessage(contactRequestDto.getMessage());
		contact.setPhone(contact.getPhone());

		return contactRepository.save(contact);


	}
	
}
