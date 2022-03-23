package com.ushwamala.book.multiplication.controller;

import com.ushwamala.book.multiplication.domain.Multiplication;
import com.ushwamala.book.multiplication.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multiplications")
@RequiredArgsConstructor
public class MultiplicationController {

    private final MultiplicationService multiplicationService;

    @GetMapping("/random")
    Multiplication getRandomMultiplication() {
        return multiplicationService.createRandomMultiplication();
    }

}
