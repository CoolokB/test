package com.test.tools;

import com.test.Page;
import com.test.Person;
import com.test.enums.TestType;
import java.util.List;

public interface Inter {

     default void initialize(PagesContainer container, TestType type, Person person){}
     default void initialize(Person person){}
     default void initialize(List<Page> list, Person person){}

}
