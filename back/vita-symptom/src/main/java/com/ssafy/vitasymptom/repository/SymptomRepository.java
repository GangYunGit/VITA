package com.ssafy.vitasymptom.repository;

import com.ssafy.vitasymptom.entity.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SymptomRepository extends JpaRepository<Symptom, Integer> {
    List<Symptom> findAll();
}
