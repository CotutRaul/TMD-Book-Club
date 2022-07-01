package org.endava.tmd.TMDBookClub.repository;

import org.endava.tmd.TMDBookClub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByNameOrEmail(String name, String email);
}