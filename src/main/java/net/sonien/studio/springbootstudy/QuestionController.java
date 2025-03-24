package net.sonien.studio.springbootstudy;

import lombok.RequiredArgsConstructor;
import net.sonien.studio.springbootstudy.question.Question;
import net.sonien.studio.springbootstudy.question.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionRepository questionRepository;

    @GetMapping("/question")
    public String list(Model model) {
        List<Question> questions = this.questionRepository.findAll();
        model.addAttribute("questions", questions);
        return "question_list";
    }
}
