package com.alkemy.ong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
