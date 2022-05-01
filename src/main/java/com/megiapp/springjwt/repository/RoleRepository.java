package com.megiapp.springjwt.repository;

import java.util.Optional;

import com.megiapp.springjwt.enums.ERole;
import com.megiapp.springjwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(ERole name);
}
