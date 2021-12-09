package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactRequestDto;
import com.alkemy.ong.model.Contact;

public interface IContactService {
    Contact addContact(ContactRequestDto contactRequestDto) throws Exception;
}
