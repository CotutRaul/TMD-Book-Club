package org.endava.tmd.TMDBookClub.repository;

import org.endava.tmd.TMDBookClub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByNameOrEmail(String name, String email);

    User findUserByEmail(String email);
}