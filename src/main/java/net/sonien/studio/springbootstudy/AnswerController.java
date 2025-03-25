package net.sonien.studio.springbootstudy;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import net.sonien.studio.springbootstudy.answer.Answer;
import net.sonien.studio.springbootstudy.answer.AnswerDTO;
import net.sonien.studio.springbootstudy.answer.AnswerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
    private final AnswerRepository answerRepository;

    @GetMapping("/{id}")
    public ApiResponse<Answer> get(@PathVariable int id) {
        Answer answer = this.answerRepository.findById(id).orElse(null);
        if (answer == null) {
            throw new ErrorResponseException(HttpStatus.NOT_FOUND);
        }
        return new ApiResponse(0, "", AnswerDTO.fromEntity(answer));
    }
}
