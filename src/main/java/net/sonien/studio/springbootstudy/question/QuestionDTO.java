package net.sonien.studio.springbootstudy.question;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import net.sonien.studio.springbootstudy.answer.Answer;
import net.sonien.studio.springbootstudy.answer.AnswerDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // Null 값인 필드 제외
public class QuestionDTO {
    static public QuestionDTO fromEntity(Question entity) {
        QuestionDTO dto = new QuestionDTO();
        dto.id = entity.getId();
        dto.subject = entity.getSubject();
        dto.content = entity.getContent();
        dto.createdDate = entity.getCreatedDate();

        if (entity.getAnswers() != null) {
            dto.answers = new ArrayList<>();
            entity.getAnswers().forEach(answer -> {
                Answer cloned = answer.mustClone();
                cloned.setQuestion(null);
                dto.answers.add(AnswerDTO.fromEntity(cloned));
            });
        } else {
            entity.setAnswers(null);
        }

        return dto;
    }

    static public List<QuestionDTO> fromEntity(List<Question> entities) {
        List<QuestionDTO> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(QuestionDTO.fromEntity(entity)));
        return dtos;
    }

    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime createdDate;
    private List<AnswerDTO> answers;
}
