package com.ssafy.vitasymptom.controller;

import com.ssafy.vitasymptom.entity.Symptom;
import com.ssafy.vitasymptom.service.SymptomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/symptom")
public class SymptomController {

    private final SymptomService symptomService;

    @GetMapping("/hello")
    public String hello() { return  "hello"; }

    @GetMapping
    public ResponseEntity<?> symptomPartList() {
        try {
            System.out.println("SymptomController 실행, symptom_part 가져오기");
            List<Symptom> symptomPartList = symptomService.getAllSymptomPart();
            if (symptomPartList != null && !symptomPartList.isEmpty()) {
                System.out.println("symptomPartList = " + symptomPartList);
                return new ResponseEntity<List<Symptom>>(symptomPartList, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    // 예외 처리 함수
    private ResponseEntity<?> exceptionHandling(Exception e) {
        e.printStackTrace();
        // 예외 발생 시 body에 에러메세지 출력
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
