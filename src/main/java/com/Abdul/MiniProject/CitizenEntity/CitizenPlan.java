package com.Abdul.MiniProject.CitizenEntity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "CitizenInfo")
public class CitizenPlan {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cid;

    private String name;

    private String email;

    private String gender;

    private String planname;

    private String planstatus;

    private Integer mobile;

    private Integer ssn;
}
