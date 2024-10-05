package com.citizensmqp.backend.repositorys;

import com.citizensmqp.backend.models.msgModel;
import com.citizensmqp.backend.models.userModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<userModel, Long> {
    @Query(value = "SELECT * FROM main.user where user.NAME = ?", nativeQuery = true)
    public List<userModel> findByName(String username);

    @Query(value = "SELECT * FROM main.user where user.EMAIL = ?", nativeQuery = true)
    public Optional<userModel> findByEmail(String email);

    @Query("SELECT user FROM userModel user left join fetch user.likes msg WHERE user.email = :email ")
    public Optional<userModel> findByEmailWithLikes(@Param("email") String email);

    @Query(value = "SELECT * FROM main.User WHERE main.userid.userid = ? LEFT JOIN ON main.User.user_id = main.likes.user_id ",nativeQuery = true)
    public Optional<userModel> getUserWithLikes(long userId);
}
