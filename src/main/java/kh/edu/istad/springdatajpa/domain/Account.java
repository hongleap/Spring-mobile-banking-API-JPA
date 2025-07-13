package kh.edu.istad.springdatajpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 32)
    private String actNo;


    @Column(nullable = false, length = 50)
    private String actCurrency;


    @Column(nullable = false)
    private BigDecimal balance;


    @Column(nullable = false)
    private Boolean isDeleted;


    @ManyToOne // many customers have one account
    @JoinColumn(name = "cust_id") // change name table relationship
    private Customer customer;

}
