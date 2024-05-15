package com.caffeinedoctor.userservice.dto.request.follow;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowRequestDto {
    @NotBlank(message = "fromUserId must not be blank")
    private Long fromUserId;
    @NotBlank(message = "toUserId must not be blank")
    private Long toUserId;
}
