package com.ushwamala.book.multiplication.event;

import java.io.Serializable;

/**
 * Event that models the fact that a {@link com.ushwamala.book.multiplication.domain.Multiplication}
 * has been solved in the system. Provides some context information about the multiplication.
 */

public record MultiplicationSolvedEvent(Long multiplicationResultAttemptId,
                                        Long userId,
                                        boolean correct) implements Serializable {

}
