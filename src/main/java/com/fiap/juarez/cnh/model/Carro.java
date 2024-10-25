package com.fiap.juarez.cnh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_carros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Carro {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CARROS")
        @SequenceGenerator(name = "SEQ_CARROS", sequenceName = "SEQ_CARROS", allocationSize = 1)
        private Long carroId;
        private String marca;
        private String modelo;
        private String placa;
}
