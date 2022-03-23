package com.ushwamala.book.multiplication.controller;

import com.ushwamala.book.multiplication.domain.MultiplicationResultAttempt;
import com.ushwamala.book.multiplication.service.MultiplicationService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class provides a REST API to POST the attempts from users.
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/results")
final class MultiplicationResultAttemptController {

    private final MultiplicationService multiplicationService;

    @PostMapping
    ResponseEntity<ResultResponse> postResult(
            @RequestBody MultiplicationResultAttempt multiplicationResultAttempt) {
        boolean correct = multiplicationService.checkAttempt(multiplicationResultAttempt);
        return ResponseEntity.ok(new ResultResponse(correct));
    }
}

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
final class ResultResponse {
    private final boolean correct;
}
