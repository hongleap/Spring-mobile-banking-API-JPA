package kh.edu.istad.springdatajpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name="segments")
public class CustomerSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true,nullable=false)
    private String customerSegment;

    private String description;

    @Column( nullable = false)
    private boolean isDeleted;

    @OneToMany(mappedBy = "customerSegment", fetch = FetchType.EAGER)
    private List<Customer> customers;

}
