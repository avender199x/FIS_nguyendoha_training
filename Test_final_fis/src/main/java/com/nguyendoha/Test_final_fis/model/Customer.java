package com.nguyendoha.Test_final_fis.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "mobile", length = 10, nullable = false)
    private String mobile;

    @Column(name = "address", length = 200, nullable = false)
    private String address;
    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private List<Order> orders;
}
