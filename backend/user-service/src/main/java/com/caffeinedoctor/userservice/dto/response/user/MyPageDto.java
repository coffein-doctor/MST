package com.caffeinedoctor.userservice.dto.response.user;

public class MyPageDto {
    private Long userId;
    private String nickname;
    private String profilePictureUrl;
    private int followerCount;
    private int followingCount;
    private int dailyWaterIntake; // 일일 물 섭취량 (ml 단위)
    private int dailyCaffeineIntake; // 일일 카페인 섭취량 (mg 단위)
    private int dailySugarIntake; // 일일 당 섭취량 (g 단위)


    // 권장 섭취량
//    private final int recommendedDailyWaterIntake = 2000; // 예시 값, 실제 권장량은 변동 가능
//    private final int recommendedDailyCaffeineIntake = 400; // 성인 기준
//    private final int recommendedDailySugarIntake = 50; // 세계보건기구(WHO) 권장량
}
