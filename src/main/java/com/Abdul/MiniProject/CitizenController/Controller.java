package com.Abdul.MiniProject.CitizenController;


import com.Abdul.MiniProject.CitizenEntity.CitizenPlan;
import com.Abdul.MiniProject.CitizenService.Service;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {


    @Autowired
    Service service;




    @GetMapping(value = "/planname")
    public ResponseEntity<List<String>> Getplanname() {
        List<CitizenPlan> getplannames = service.GetPlanNames();
        return new ResponseEntity<CitizenPlan>(getplannames, HttpStatus.OK);
    }

    @GetMapping(value = "/planstatus")
    public ResponseEntity<List<String>> getplanstatus() {
        List<CitizenPlan> planStatus = service.GetPlanStatus();
        return new ResponseEntity<>(planStatus, HttpStatus.OK);
    }

    @GetMapping("/excel")
    public void generateExcelReport(HttpServletResponse response) throws Exception {

        response.setContentType("application/octet-stream");


        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=courses.xls";

        response.setHeader(headerKey, headerValue);


        service.ExportExcel(response);

        response.flushBuffer();
    }

    @GetMapping("/pdf")
    public void generatePdfReport(HttpServletResponse response) throws Exception {

        response.setContentType("application/pdf");

        String Key = "Content-Disposition";
        String Value = "attachment;plans";

        response.setHeader(Key, Value);


        service.ExportPdf(response);

        response.flushBuffer();
    }


}
