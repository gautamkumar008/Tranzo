package com.Tranzo.Repository;

import com.Tranzo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    boolean existsByMobileNo(String mobileNo);

    User findByEmail(String email);
}
