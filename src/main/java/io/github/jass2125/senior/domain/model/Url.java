package io.github.jass2125.senior.domain.model;

import lombok.AllArgsConstructor;
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
@SequenceGenerator(name = "hibernate_sequence", initialValue = 1)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Url implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    private Long id;

    private String currentUrl;
    private String newUrl;
    private LocalDate dateCreation;

    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDate.now();
    }

}
