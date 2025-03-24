package net.sonien.studio.springbootstudy.question;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.sonien.studio.springbootstudy.answer.Answer;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answers;
}
