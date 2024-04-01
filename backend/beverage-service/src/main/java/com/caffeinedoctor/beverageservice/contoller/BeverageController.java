package com.caffeinedoctor.beverageservice.contoller;

import com.caffeinedoctor.beverageservice.dto.ApiResponse;
import com.caffeinedoctor.beverageservice.dto.BasicInfoRequest;
import com.caffeinedoctor.beverageservice.dto.BeverageBasicInfo;
import com.caffeinedoctor.beverageservice.service.BeverageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BeverageController {
    private final BeverageService beverageService;

    @GetMapping("/beverage")
    public ResponseEntity<ApiResponse<BeverageBasicInfo>> basicInfo(@RequestBody BasicInfoRequest dto){
        BeverageBasicInfo beverageBasicInfo = beverageService.basicInfo(dto);
        ApiResponse<BeverageBasicInfo> result = new ApiResponse<>(true, "음료 정보 조회 성공", beverageBasicInfo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
