package com.ssafy.vitasymptom.service;

import com.ssafy.vitasymptom.entity.Symptom;
import com.ssafy.vitasymptom.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SymptomServiceImpl implements SymptomService {

    @Autowired
    private SymptomRepository symptomRepository;

    @Override
    public List<Symptom> getAllSymptomPart() {
        System.out.println("증상 부위 목록 가져오기");
        return symptomRepository.findAll();
    }
}
