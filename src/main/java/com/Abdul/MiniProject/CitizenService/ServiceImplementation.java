package com.Abdul.MiniProject.CitizenService;

import com.Abdul.MiniProject.CitizenEntity.CitizenPlan;
import com.Abdul.MiniProject.CitizenEntity.SearchRequest;
import com.Abdul.MiniProject.CitizenRepo.Repo;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Example;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


@org.springframework.stereotype.Service
public class ServiceImplementation implements Service {


    Repo repo;

    public ServiceImplementation(Repo repo) {
        this.repo = repo;
    }

    @Override
    public List<CitizenPlan> GetPlanNames() {
        return repo.GetPlanName();

    }

    @Override
    public List<CitizenPlan> GetPlanStatus() {
        return repo.GetPlanStatus();
    }

    @Override
    public List<CitizenPlan> GetCitizenPlan(SearchRequest request) {
        CitizenPlan entity = new CitizenPlan();
        if (request.getPlanname() != null && !request.getPlanname().equals("")) {
            entity.setPlanname(request.getPlanname());
        }

        if (request.getPlanstatus() != null && !request.getPlanstatus().equals("")) {
            entity.setPlanstatus(request.getPlanstatus());
        }

        Example<CitizenPlan> example = Example.of(entity);

        List<CitizenPlan> records = repo.findAll(example);
        return records;
    }

    @Override
    public void ExportExcel(HttpServletResponse response) throws IOException {


        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Citizens Info").getWorkbook().createSheet();

        XSSFRow headerRow = sheet.createRow(0);


        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("SSN");
        headerRow.createCell(3).setCellValue("Gender");
        headerRow.createCell(4).setCellValue("Plan Names");
        headerRow.createCell(5).setCellValue("Plan Status");


        List<CitizenPlan> records = repo.findAll();

        int datarowindex = 1;

        for (CitizenPlan record : records) {
            XSSFRow dataRow = sheet.createRow(datarowindex);

            dataRow.createCell(0).setCellValue(record.getCid());
            dataRow.createCell(1).setCellValue(record.getName());
            dataRow.createCell(2).setCellValue(record.getSsn());
            dataRow.createCell(3).setCellValue(record.getGender());
            dataRow.createCell(4).setCellValue(record.getPlanname());
            dataRow.createCell(5).setCellValue(record.getPlanstatus());


            datarowindex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();


    }

    @Override
    public void ExportPdf(HttpServletResponse response) throws IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Citizen Info", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 1.5f});
        table.setSpacingBefore(10);

        // Set table header data
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font f = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID", f));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SSN", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Gender", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Plan Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Plan Status", font));
        table.addCell(cell);

        // set table data

        List<CitizenPlan> records = repo.findAll();
        for (CitizenPlan record : records) {
            table.addCell(String.valueOf(record.getCid()));
            table.addCell(record.getName());
            table.addCell(String.valueOf(record.getSsn()));
            table.addCell(record.getGender());
            table.addCell(record.getPlanname());
            table.addCell(record.getPlanstatus());
        }


        document.add(table);
        document.close();

    }
}
