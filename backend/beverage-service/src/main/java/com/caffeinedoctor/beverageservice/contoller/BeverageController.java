package com.caffeinedoctor.beverageservice.contoller;

import com.caffeinedoctor.beverageservice.dto.*;
import com.caffeinedoctor.beverageservice.service.BeverageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "음료 관리")
@RestController
@RequiredArgsConstructor
public class BeverageController {
    private final BeverageService beverageService;

    @Operation(summary = "음료 조회")
    @GetMapping("/beverage")
    public ResponseEntity<ApiResponse<List<BeverageBasicInfo>>> basicInfo(@RequestBody BasicInfoRequest dto){
        List<BeverageBasicInfo> beverageBasicInfo = beverageService.basicInfo(dto);
        ApiResponse<List<BeverageBasicInfo>> result = new ApiResponse<>(true, "음료 정보 조회 성공", beverageBasicInfo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "커스텀 음료 등록")
    @PostMapping("/beverage/custom")
    public ResponseEntity<ApiResponse<BeverageCustomInfo>> customInfo(@RequestBody CustomCreateRequest dto){
        BeverageCustomInfo beverageCustomInfo = beverageService.customCreate(dto);
        ApiResponse<BeverageCustomInfo> result = new ApiResponse<>(true, "커스텀 음료 등록 성공", beverageCustomInfo);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(summary = "기존 음료 등록")
    @PostMapping("/beverage")
    public ResponseEntity<ApiResponse> beverageCreate(@RequestBody BasicCreateRequest dto) {
        beverageService.basicCreate(dto);
        ApiResponse result = new ApiResponse<>(true, "기존 음료 등록 성공");
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
