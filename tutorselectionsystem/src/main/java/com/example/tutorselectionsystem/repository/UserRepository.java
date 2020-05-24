package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Integer> {

    @Query("from User u where u.number = :num")
   Optional<User>  findById(@Param("num") int num);
}
