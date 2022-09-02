package com.worker.worker.service;

import com.worker.worker.model.Choice;
import com.worker.worker.model.Vote;
import com.worker.worker.repository.ChoiceRepository;
import com.worker.worker.repository.PollRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    @Autowired
    PollRepository pollRepository;

    @Autowired
    ChoiceRepository choiceRepository;

    @RabbitListener(queues = "voting_queue")
    public void listener(Vote vote) {
        Choice choice = choiceRepository.findChoiceByIdAndPollId(vote.getChoiceId(), vote.getPollId()).orElse(null);
        if (choice != null) {
            choice.setVotes(choice.getVotes() + 1);
            choiceRepository.save(choice);
            System.out.println("Vote submitted for: " + choice.getText());
        }
    }
}
