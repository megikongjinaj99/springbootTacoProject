package com.megiapp.springjwt.repository;

import com.megiapp.springjwt.enums.ERole;
import com.megiapp.springjwt.models.Role;
import com.megiapp.springjwt.models.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TacoRepository extends JpaRepository<Taco, Integer> {
    Optional<Role> findByName(ERole name);

    Taco findTacoById(Integer id);

    Taco findByName(String tacoName);
}
