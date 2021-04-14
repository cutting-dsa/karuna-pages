package com.karuna.pages.question.service;

import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.model.Question;

import java.util.function.BiFunction;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuestionAnswerUtil {
    static BiFunction<List<Answer>, Integer, List<Question>> computeMostAnsweredQuestions =
            (answers, k) -> getQuestionStream(answers, k)
                    .collect(Collectors.toList());


    static Function<List<Answer>, Optional<Question>> getMostAnsweredQuestion =
            (answers) -> getQuestionStream(answers, 1)
                    .findFirst();

    private static Stream<Question> getQuestionStream(List<Answer> answers, Integer k) {
        return answers.stream()
                .collect(Collectors.groupingBy((Answer::getQuestion), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .limit(k)
                .map(Map.Entry::getKey);
    }

}
