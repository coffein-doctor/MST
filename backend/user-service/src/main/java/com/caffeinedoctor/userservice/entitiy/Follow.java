package com.caffeinedoctor.userservice.entitiy;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "follow")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id
    @GeneratedValue
    @Column(name = "follow_id")
    private Long id;

    // followerId는 팔로우하는 사용자 ID, followingId는 팔로우되는 사용자 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id")
    private User fromUser; // 팔로우하는 사람

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id")
    private User toUser; // 팔로우 받는 사람

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Follow(User fromUser, User toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
    }
}
