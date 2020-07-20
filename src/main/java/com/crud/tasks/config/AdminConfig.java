package com.crud.tasks.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("tranquil.eyrie@gmail.com")
    private String adminMail;
}