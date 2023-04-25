package com.touchtunes.abtestingpoc.repository;

import com.touchtunes.abtestingpoc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
