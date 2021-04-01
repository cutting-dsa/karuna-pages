package com.karuna.pages.question.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer1 = (Answer) o;
        return Objects.equals(id, answer1.id) &&
                Objects.equals(answer, answer1.answer) &&
                Objects.equals(created_at, answer1.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answer, created_at);
    }
}
