package com.test;

import com.test.enums.TestType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Component
@ToString
public class Page {

    private int points;
    private TestType type;
    private String answer;
    private String question;
    private List<String> answers;
    private Map<String,Integer> answersAndPoints;

    public void setAnswers(List<String> answersList) {

        answers = new ArrayList<>();
        answersAndPoints = new LinkedHashMap<>();

        for (String s : answersList) {
            String temp = s.substring(2, s.length() - 2);
            answers.add(temp);
            answersAndPoints.put(temp, Integer.parseInt(s.substring(s.length() - 2).trim()));
        }

    }

    public void setAnswer(String answer) {

        this.answer = answer;
        points=answersAndPoints.get(answer);

    }
}
