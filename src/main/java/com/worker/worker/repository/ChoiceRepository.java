package com.worker.worker.repository;

import com.worker.worker.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    Optional<Choice> findChoiceByIdAndPollId(Long choiceId, Long pollId);
}
