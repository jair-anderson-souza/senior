package io.github.jass2125.senior.controller;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@Entity
@SequenceGenerator(name = "url_sequence", schema = "url_sequence")
@Builder
public class Url implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_sequence")
    private Long id;

    private String currentUrl;
    private String newUrl;
    private LocalDate dateCreation;


    @PrePersist
    public void prePersist(){
        this.dateCreation = LocalDate.now();
    }

}
