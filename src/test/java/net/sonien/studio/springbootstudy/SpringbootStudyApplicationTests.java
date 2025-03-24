package net.sonien.studio.springbootstudy;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import net.sonien.studio.springbootstudy.answer.Answer;
import net.sonien.studio.springbootstudy.answer.AnswerRepository;
import net.sonien.studio.springbootstudy.question.Question;
import net.sonien.studio.springbootstudy.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class SpringbootStudyApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Rollback(true)
    @Test
    void testJpa() {

//        questionRepository.deleteAll();

        Question q1 = new Question();
        q1.setSubject("질문 1");
        q1.setContent("질문 1에 대한 내용입니다.");
        q1.setCreatedDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("질문 2");
        q2.setContent("질문 2에 대답하라우 동무.");
        q2.setCreatedDate(LocalDateTime.now());
        questionRepository.save(q2);


        questionRepository.findAll().forEach(question -> {
            IntStream.range(0, 5).forEach(i -> {
                Answer answer = new Answer();
                answer.setQuestion(question);
                answer.setCreatedDate(LocalDateTime.now());
                answer.setContent(String.format("질문 \"%s\"에 대한 답변 %d 입니다.", question.getSubject(), i));
                answerRepository.save(answer);
            });
        });
        answerRepository.findAll(Sort.by(Sort.Direction.DESC, "content")).forEach(answer -> {
            System.out.println(answer.getContent());
        });
        System.out.println(answerRepository.count());

        entityManager.clear();
        Optional<Question> optQ1 = questionRepository.findById(q1.getId());
        if (optQ1.isPresent()) {
            Question q = optQ1.get();
            List<Answer> answers = q.getAnswers();
            answers.forEach(answer -> {
                System.out.println(answer.getContent());
            });
        }
        questionRepository.delete(optQ1.get());
        System.out.println(answerRepository.count());
    }
}
