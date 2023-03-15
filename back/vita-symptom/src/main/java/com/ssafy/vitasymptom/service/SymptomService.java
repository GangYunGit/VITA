package com.ssafy.vitasymptom.service;

import com.ssafy.vitasymptom.dto.SymptomPartListDto;
import com.ssafy.vitasymptom.entity.Symptom;

import java.util.List;

public interface SymptomService {
    List<Symptom> getAllSymptom();
    List<String> getAllSymptomPartList();
}