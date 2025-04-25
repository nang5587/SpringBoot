package com.rubypapar.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rubypapar.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
