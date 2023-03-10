package com.ssafy.vitasymptom.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "symptom_list")
public class SymptomList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long symptomListId;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Symptom.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "symptom_id")
    private Symptom symptom;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Disease.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "disease_id")
    private Disease disease;
}
