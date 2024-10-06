package com.citizensmqp.backend.repositorys;

import com.citizensmqp.backend.models.msgModel;
import com.citizensmqp.backend.models.testModel;
import com.citizensmqp.backend.models.userModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface msgRepository extends JpaRepository<msgModel, Long> {
    @Query("SELECT msg FROM msgModel msg left join fetch msg.comments comments WHERE msg.message_id = :id ")
    public Optional<msgModel> getMessageWithComments(@Param("id") long id);

    @Query("SELECT msg FROM msgModel msg left join fetch msg.usersLiked likes WHERE msg.message_id = :id ")
    public Optional<msgModel> getMessageWithLikes(@Param("id") long id);

    @Query("SELECT msg FROM msgModel msg LEFT join fetch msg.comments comments left join fetch msg.usersLiked users WHERE msg.message_id = :id ")
    public Optional<msgModel> getMessageByIdFull(@Param("id") long id);
}