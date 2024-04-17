package com.caffeinedoctor.beverageservice.dto;

import com.caffeinedoctor.beverageservice.entity.DataFile;
import com.caffeinedoctor.beverageservice.util.UriUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataFileInfo {
    private Long id;
    private String originalFileName;
    private String url;

    public DataFileInfo(DataFile dataFile) {
        this.id = dataFile.getId();
        this.originalFileName = dataFile.getOriginalFileName();
        this.url = UriUtil.buildUri("/datafiles/" + this.id.toString());
    }
}
