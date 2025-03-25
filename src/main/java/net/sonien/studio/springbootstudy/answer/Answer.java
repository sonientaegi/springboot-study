package net.sonien.studio.springbootstudy.answer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.sonien.studio.springbootstudy.question.Question;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Answer mustClone() {
        try {
            return (Answer)this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
