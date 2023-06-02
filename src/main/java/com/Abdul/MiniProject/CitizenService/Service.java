package com.Abdul.MiniProject.CitizenService;

import com.Abdul.MiniProject.CitizenEntity.CitizenPlan;
import com.Abdul.MiniProject.CitizenEntity.SearchRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface Service {


    public List<CitizenPlan> GetPlanNames();

    public List<CitizenPlan> GetPlanStatus();

    public List<CitizenPlan> GetCitizenPlan(SearchRequest request);


    public void ExportExcel(HttpServletResponse response) throws IOException;

    public void ExportPdf(HttpServletResponse response) throws IOException;
}
