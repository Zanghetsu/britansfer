package com.zanghetsu.britansfer.email;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator  implements Predicate<String> {
    @Override
    public boolean test(String s) {
        //TODO:validate
        return true;
    }
}
