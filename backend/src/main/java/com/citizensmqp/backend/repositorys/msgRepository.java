package com.citizensmqp.backend.repositorys;

import com.citizensmqp.backend.models.msgModel;
import com.citizensmqp.backend.models.testModel;
import com.citizensmqp.backend.models.userModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface msgRepository extends JpaRepository<msgModel, Long> {

}