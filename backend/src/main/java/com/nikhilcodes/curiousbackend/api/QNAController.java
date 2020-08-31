package com.nikhilcodes.curiousbackend.api;

import com.nikhilcodes.curiousbackend.model.AnswerModel;
import com.nikhilcodes.curiousbackend.model.QNAModel;
import com.nikhilcodes.curiousbackend.service.QNAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public void addQuestion(@RequestBody QNAModel question) {
        qnaService.addQuestion(question);
    }

    @GetMapping(path = "/{id}")
    public Optional<QNAModel> getQNA(@PathVariable("id") String id) {
        return qnaService.getQNA(id);
    }

    @PutMapping(path = "/{id}")
    public void addAnswer(@PathVariable("id") String id, @RequestBody AnswerModel answer) {
        qnaService.addAnswer(id, answer);
    }
}
