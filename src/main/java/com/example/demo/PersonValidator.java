package com.example.demo;

import com.example.demo.addresses.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreatePersonValidator")
public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Person person = (Person) o;
        if (checkInputString(person.getFirstName())) {
            errors.rejectValue("firstName", "first.name.empty", "First name cannot be null");
        }

    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}


