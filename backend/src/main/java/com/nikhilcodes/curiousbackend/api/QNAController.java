package com.nikhilcodes.curiousbackend.api;

import com.nikhilcodes.curiousbackend.model.QNAModel;
import com.nikhilcodes.curiousbackend.service.QNAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        if(page == null) {
            page = 0;
        }
        return qnaService.get10QNAs(page * 10);
    }
}
