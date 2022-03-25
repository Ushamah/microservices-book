package com.ushwamala.book.multiplication.service;

import com.ushwamala.book.multiplication.domain.Multiplication;
import com.ushwamala.book.multiplication.domain.MultiplicationResultAttempt;
import com.ushwamala.book.multiplication.domain.User;
import com.ushwamala.book.multiplication.event.EventDispatcher;
import com.ushwamala.book.multiplication.event.MultiplicationSolvedEvent;
import com.ushwamala.book.multiplication.repository.MultiplicationResultAttemptRepository;
import com.ushwamala.book.multiplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
class MultiplicationServiceImpl implements MultiplicationService {

    private final RandomGeneratorService randomGeneratorService;
    private final MultiplicationResultAttemptRepository attemptRepository;
    private final UserRepository userRepository;
    private final EventDispatcher eventDispatcher;


    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Transactional
    @Override
    public boolean checkAttempt(final MultiplicationResultAttempt attempt) {
        // Check if the user already exists for that alias
        Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());

        // Avoids 'hack' attempts
        Assert.isTrue(!attempt.isCorrect(), "You can't send an attempt marked as correct!!");

        // Check if the attempt is correct
        int factorA = attempt.getMultiplication().getFactorA();
        int factorB = attempt.getMultiplication().getFactorB();
        boolean isCorrect = attempt.getResultAttempt() == factorA * factorB;

        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
                user.orElse(attempt.getUser()),
                attempt.getMultiplication(),
                attempt.getResultAttempt(),
                isCorrect
        );

        // Stores the attempt
        attemptRepository.save(checkedAttempt);

        //communicates the result via Event
        eventDispatcher.send(
                new MultiplicationSolvedEvent(
                        checkedAttempt.getId(),
                        checkedAttempt.getUser().getId(),
                        checkedAttempt.isCorrect()
                )
        );

        return isCorrect;
    }

    @Override
    public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
        return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
    }

    @Override
    public Optional<MultiplicationResultAttempt> getById(Long id) {
        return attemptRepository.findById(id);
    }

}