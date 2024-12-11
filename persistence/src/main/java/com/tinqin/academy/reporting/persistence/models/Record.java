package com.tinqin.academy.reporting.persistence.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "records")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "is_closed")
    private Boolean isClosed;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER, mappedBy = "record")
    @JsonManagedReference  ///  to avoid cycle
    private List<Event> eventsList = new ArrayList<>();
}
