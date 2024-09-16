package com.citizensmqp.backend.repositorys;

import com.citizensmqp.backend.models.testModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface testRepository extends JpaRepository<testModel, Long> {
}
