package net.sonien.studio.springbootstudy.answer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import net.sonien.studio.springbootstudy.question.Question;
import net.sonien.studio.springbootstudy.question.QuestionDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // Null 값인 필드 제외
public class AnswerDTO {
    public static AnswerDTO fromEntity(Answer entity) {
        AnswerDTO dto = new AnswerDTO();
        dto.id = entity.getId();
        dto.content = entity.getContent();
        dto.createdDate = entity.getCreatedDate();

        if (entity.getQuestion() != null) {
            Question cloned = entity.getQuestion().mustClone();
            cloned.setAnswers(null);
            dto.question = QuestionDTO.fromEntity(cloned);
        } else {
            dto.question = null;
        }

        return dto;
    }

    public static List<AnswerDTO> fromEntity(List<Answer> entities) {
        List<AnswerDTO> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(AnswerDTO.fromEntity(entity)));
        return dtos;
    }

    private Integer id;
    private String content;
    private LocalDateTime createdDate;
    private QuestionDTO question;
}
