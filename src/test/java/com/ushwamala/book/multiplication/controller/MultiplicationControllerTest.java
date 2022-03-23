package com.ushwamala.book.multiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ushwamala.book.multiplication.domain.Multiplication;
import com.ushwamala.book.multiplication.service.MultiplicationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(MultiplicationController.class)
public class MultiplicationControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<Multiplication> json;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getRandomMultiplicationTest() throws Exception{

        // given
        given(multiplicationService.createRandomMultiplication())
                .willReturn(new Multiplication(70, 20));

        // when
        MediaType json = MediaType.APPLICATION_JSON;
        MockHttpServletResponse response = mvc.perform(get("/multiplications/random")
                        .accept(json)).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        Multiplication multiplication = new Multiplication(70, 20);
        String responseContentAsString = response.getContentAsString();
        String jsonContent = this.json.write(multiplication).getJson();
        assertThat(responseContentAsString).isEqualTo(jsonContent);
    }
}
