package com.ssafy.vitasymptom.service;

import com.ssafy.vitasymptom.dto.SymptomPartListDto;
import com.ssafy.vitasymptom.entity.Symptom;
import com.ssafy.vitasymptom.repository.SymptomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SymptomServiceImpl implements SymptomService {

    @Autowired
    private SymptomRepository symptomRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<Symptom> getAllSymptom() {
        System.out.println("증상 부위 목록 가져오기");
        return symptomRepository.findAll();
    }

    @Override
    public List<String> getAllSymptomPartList() {
        return symptomRepository.findAll()
                .stream()
                .map(symptom -> modelMapper.map(symptom.getSymptomName(), String.class))
                .collect(Collectors.toList());
    }

}
