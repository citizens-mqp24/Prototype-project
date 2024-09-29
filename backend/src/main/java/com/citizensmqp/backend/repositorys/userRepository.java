package com.citizensmqp.backend.repositorys;

import com.citizensmqp.backend.models.userModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<userModel, Long> {
    @Query(value = "SELECT USER_ID,NAME,EMAIL,PICTURE FROM main.user where user.NAME = ?", nativeQuery = true)
    public List<userModel> findByName(String username);

    @Query(value = "SELECT USER_ID,NAME,EMAIL,PICTURE FROM main.user where user.EMAIL = ?", nativeQuery = true)
    public Optional<userModel> findByEmail(String email);

}
