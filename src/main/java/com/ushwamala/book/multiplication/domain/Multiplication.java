package com.ushwamala.book.multiplication.domain;

import lombok.Getter;

@Getter
public class Multiplication {
    // Both factors
    private final int factorA;
    private final int factorB;
    // The result of the operation A * B
    private final int result;

    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
        this.result = factorA * factorB;
    }

    @Override
    public String toString() {
        return "Multiplication{" +
                "factorA=" + factorA +
                ", factorB=" + factorB +
                ", result(A*B)=" + result +
                '}';
    }

}
