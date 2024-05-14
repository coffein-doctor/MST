package com.caffeinedoctor.userservice.common.util;

import com.caffeinedoctor.userservice.enums.ActivityLevel;
import com.caffeinedoctor.userservice.enums.Gender;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HealthProfileUtil {

    public static final Double WATER_INTAKE_RECOMMENDATION = 2000.0; // ml
    public static final Double CAFFEINE_INTAKE_RECOMMENDATION = 400.0; // mg

    // 표준체중 계산
    //- 남자의 경우 : 표준체중(kg) = 키(m)의 제곱 × 22
    //- 여자의 경우 : 표준체중(kg) = 키(m)의 제곱 × 21
    private static double calculateStandardWeight(double height, Gender gender) {
        switch (gender) {
            case MALE:
                return Math.pow(height/100, 2) * 22;
            case FEMALE:
                return Math.pow(height/100, 2) * 21;
            default:
                throw new IllegalArgumentException("Invalid gender");
        }
    }

    // 하루 권장 칼로리 계산
    //- 육체활동이 거의 없는 경우 (사무직 등) : 표준체중 × 25~30 (kcal/일)
    //- 보통의 활동을 하는 경우 (영업직 등) : 표준체중 × 30~35 (kcal/일)
    //- 심한 육체활동을 하는 경우 (운동강사, 노동직 등) : 표준체중 × 35~40 (kcal/일)
    private static double calculateRecommendedCalories(double standardWeight, ActivityLevel level) {
        switch (level) {
            case LOW:
                return standardWeight * 25; // 최소값 사용
            case MEDIUM:
                return standardWeight * 30; // 최소값 사용
            case HIGH:
                return standardWeight * 35; // 최소값 사용
            default:
                throw new IllegalArgumentException("Invalid activity level");
        }
    }

    // 하루 권장 당 섭취량 계산
    private static double calculateSugarIntake(double recommendedCalories) {
        return (recommendedCalories * 0.1) / 4;
    }

    public static double calculateRecommendedSugarIntake(double height, Gender gender, ActivityLevel activityLevel) {
        double standardWeight = calculateStandardWeight(height, gender);
        double recommendedCalories = calculateRecommendedCalories(standardWeight, activityLevel);
        double recommendedSugarIntake = calculateSugarIntake(recommendedCalories);

        log.info("하루 권장 당 섭취량: " + recommendedSugarIntake + "g");

        return recommendedSugarIntake;
    }

}
