package com.ak.api.services;

import com.ak.entities.User;

public interface IActivatorService {

    void addActivator(User user, String email);

    void activation(String code);

}
