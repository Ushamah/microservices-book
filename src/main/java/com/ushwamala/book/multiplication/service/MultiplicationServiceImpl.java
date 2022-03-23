package com.ushwamala.book.multiplication.service;

import com.ushwamala.book.multiplication.domain.Multiplication;
import com.ushwamala.book.multiplication.domain.MultiplicationResultAttempt;
import org.springframework.stereotype.Service;

@Service
public record MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService)
        implements MultiplicationService {

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Override
    public boolean checkAttempt(final MultiplicationResultAttempt resultAttempt) {
        int factorA = resultAttempt.getMultiplication().getFactorA();
        int factorB = resultAttempt.getMultiplication().getFactorB();
        return resultAttempt.getResultAttempt() == factorA * factorB;
    }
}
