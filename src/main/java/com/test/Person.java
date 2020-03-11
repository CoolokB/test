package com.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Component
public class Person {

    private String name;
    private String lastName;
    private int result111;
    private int result113;
    private int profile;

}
