package com.Abdul.MiniProject.CitizenRepo;

import com.Abdul.MiniProject.CitizenEntity.CitizenPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface Repo extends JpaRepository<CitizenPlan, Serializable> {


    @Query("select distinct (planname) from CitizenPlan")
    public List<CitizenPlan> GetPlanName();

    @Query("select distinct (planstatus) from CitizenPlan")
    public List<CitizenPlan> GetPlanStatus();


}
