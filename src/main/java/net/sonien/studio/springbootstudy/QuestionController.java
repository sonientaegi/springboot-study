package net.sonien.studio.springbootstudy;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import net.sonien.studio.springbootstudy.question.Question;
import net.sonien.studio.springbootstudy.question.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionRepository questionRepository;

    @GetMapping("")
    public ApiResponse<List<Question>> list() {
        List<Question> questions = this.questionRepository.findAll();
        return new ApiResponse(0, "", questions);
    }

    @GetMapping("/{id}")
    public ApiResponse<Question> get(@PathVariable int id) {
        Question question = this.questionRepository.findById(id).orElse(null);
        if (question == null) {
            throw new ErrorResponseException(HttpStatus.NOT_FOUND);

        }
        question.getAnswers().forEach(answer -> answer.setQuestion(null));
        return new ApiResponse(0, "", question);
    }
}
