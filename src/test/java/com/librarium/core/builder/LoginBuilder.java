package com.librarium.core.builder;

import com.librarium.core.app.common.model.LoginDTO;

public class LoginBuilder {

    LoginDTO loginDTO = new LoginDTO();

    public LoginBuilder buildDefaultLogin() {

        loginDTO.setUsername("metehan");
        loginDTO.setPassword("0123456789M!");

        return this;
    }

    public LoginBuilder withUsername(String username) {
        loginDTO.setUsername(username);
        return this;
    }

    public LoginBuilder withPassword(String password) {
        loginDTO.setPassword(password);
        return this;
    }

    public LoginDTO build() {
        return loginDTO;
    }

}
