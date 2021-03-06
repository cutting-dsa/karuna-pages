package com.karuna.pages.question.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.karuna.pages.category.model.Category;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.user.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","answers"})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "category", referencedColumnName = "id")
    private Category category;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true, mappedBy = "question")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Answer> answers;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Question question = (Question) obj;
        return status == question.status &&
                Objects.equals(id, question.id) &&
                Objects.equals(name, question.name) &&
                Objects.equals(category, question.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, category);
    }
}
