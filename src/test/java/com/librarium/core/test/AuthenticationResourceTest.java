package com.librarium.core.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarium.core.app.user.model.UserDTO;
import com.librarium.core.builder.UserBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationResourceTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerTest() throws Exception {

        final String registerUrl = "/public-app-api/register";

        UserDTO userDTO = new UserBuilder()
                .buildDefaultUser()
                .build();

        ResultActions resultActions = this.mockMvc
                .perform(
                        (post(registerUrl))
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Accept", "application/json; charset=UTF-8")
                                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();

        Boolean aBoolean = objectMapper.readValue(contentAsString, Boolean.class);

        Assertions.assertTrue(aBoolean);

        //Try to register the same user again and expect false as the result

        ResultActions resultActions2 = this.mockMvc
                .perform(
                        (post(registerUrl))
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Accept", "application/json; charset=UTF-8")
                                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk());

        String contentAsString2 = resultActions2.andReturn().getResponse().getContentAsString();

        Boolean aBoolean2 = objectMapper.readValue(contentAsString2, Boolean.class);

        Assertions.assertFalse(aBoolean2);
    }

}
