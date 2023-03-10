package com.ssafy.vitasymptom.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "disease")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diseaseId;

    private String diseaseName;
    private String diseaseImg;
    private String diseaseRelated;
    private String diseaseSymptoms;
    private String diseaseDepartment;
    private String diseaseDefine;
    private String diseaseCause;
    private String diseaseDiagnose;
    private String diseaseTreat;
}
