package com.alkemy.ong.repositories;

import com.alkemy.ong.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
