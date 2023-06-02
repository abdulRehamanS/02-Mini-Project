package com.Abdul.MiniProject.CitizenEntity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

    private String planname;
    private String planstatus;
}
