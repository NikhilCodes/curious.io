package com.nikhilcodes.curiousbackend.api;

import com.nikhilcodes.curiousbackend.model.AnswerModel;
import com.nikhilcodes.curiousbackend.model.QNAModel;
import com.nikhilcodes.curiousbackend.service.QNAService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Content-Type", "Access-Control-Allow-Origin", "Accept", "X-Requested-With", "remember-me"}, allowCredentials = "true")
@RestController
@RequestMapping("/api/qna")
public class QNAController {
    final QNAService qnaService;

    @Autowired
    public QNAController(QNAService qnaService) {
        this.qnaService = qnaService;
    }

    @GetMapping
    public List<QNAModel> getQNAs(@RequestParam(value = "p", required = false) Integer page) {
        if (page == null) {
            page = 0;
        }
        return qnaService.get10QNAs(page * 10);
    }

    @PostMapping
    public void addQuestion(@CurrentSecurityContext(expression = "authentication.name") String email, @RequestBody QNAModel question) {
        qnaService.addQuestion(question, email);
    }

    @GetMapping(path = "/{id}")
    public Optional<QNAModel> getQNA(@PathVariable("id") int id) {
        return qnaService.getQNA(id);
    }

    @PutMapping(path = "/{id}")
    public void addAnswer(@CurrentSecurityContext(expression = "authentication.name") String email, @PathVariable("id") int id, @RequestBody AnswerModel answer) {
        qnaService.addAnswer(answer, id, email);
    }

    @PutMapping(path = "/{id}/vote")
    public List<Integer> toggleVote(@CurrentSecurityContext(expression = "authentication.name") String email, @PathVariable("id") int id) {
        return qnaService.toggleVote(id, email);
    }

    @PutMapping(path = "/{q_id}/{a_id}/upvote")
    public List<Integer> upVoteAnswer(@CurrentSecurityContext(expression = "authentication.name") String email, @PathVariable("q_id") int q_id, @PathVariable("a_id") int a_id) {
        return qnaService.upVoteAnswer(q_id, a_id, email);
    }

    @PutMapping(path = "/{q_id}/{a_id}/downvote")
    public List<Integer> downVoteAnswer(@CurrentSecurityContext(expression = "authentication.name") String email, @PathVariable("q_id") int q_id, @PathVariable("a_id") int a_id) {
        return qnaService.downVoteAnswer(q_id, a_id, email);
    }
}
