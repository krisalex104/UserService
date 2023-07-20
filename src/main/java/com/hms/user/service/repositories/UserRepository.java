package com.hms.user.service.repositories;

import com.hms.user.service.entities.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.id = ?1 and u.activeStatus = ?2")
    Optional<User> fetchUser(Integer id, Integer statusId);

}
