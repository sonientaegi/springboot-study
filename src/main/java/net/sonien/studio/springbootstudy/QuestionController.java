package net.sonien.studio.springbootstudy;

import lombok.RequiredArgsConstructor;
import net.sonien.studio.springbootstudy.question.Question;
import net.sonien.studio.springbootstudy.question.QuestionDTO;
import net.sonien.studio.springbootstudy.question.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return new ApiResponse(0, "", QuestionDTO.fromEntity(questions));
    }

    @GetMapping("/{id}")
    public ApiResponse<Question> get(@PathVariable int id) {
        Question question = this.questionRepository.findById(id).orElse(null);
        if (question == null) {
            throw new ErrorResponseException(HttpStatus.NOT_FOUND);

        }
        return new ApiResponse(0, "", QuestionDTO.fromEntity(question));
    }
}
