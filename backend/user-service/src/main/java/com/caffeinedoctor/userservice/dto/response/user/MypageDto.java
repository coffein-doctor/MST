package com.caffeinedoctor.userservice.dto.response.user;

import com.caffeinedoctor.userservice.common.util.HealthProfileUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MypageDto {
    private Long userId;
    private String nickname;
    private String introduction;
    private String profilePictureUrl;
    private long followerCount;
    private long followingCount;
    // 권장 섭취량
    private double recommendedDailyWaterIntake; // 물(ml 단위) 권장량 2000ml
    private double recommendedDailyCaffeineIntake; // 카페인(mg 단위) 성인 기준 권장량 400mg
    private double recommendedDailySugarIntake; // 당(g 단위) 세계보건기구(WHO) 권장량 50g

//    private int dailyWaterIntake; // 일일 물 섭취량 (ml 단위)
//    private int dailyCaffeineIntake; // 일일 카페인 섭취량 (mg 단위)
//    private int dailySugarIntake; // 일일 당 섭취량 (g 단위)

    @Builder
    public MypageDto(Long userId, String nickname, String profilePictureUrl, String introduction,
                     long followerCount, long followingCount, double recommendedDailySugarIntake
    ){
        this.userId = userId;
        this.nickname = nickname;
        this.profilePictureUrl = profilePictureUrl;
        this.introduction = introduction;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.recommendedDailyWaterIntake = HealthProfileUtil.WATER_INTAKE_RECOMMENDATION;
        this.recommendedDailyCaffeineIntake = HealthProfileUtil.CAFFEINE_INTAKE_RECOMMENDATION;
        this.recommendedDailySugarIntake = recommendedDailySugarIntake;
    }


}
