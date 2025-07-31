package com.team5.on_stage.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team5.on_stage.user.entity.User;
import com.team5.on_stage.user.enums.Verified;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.team5.on_stage.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserQueryDslRepositoryImpl implements UserQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public User findByUsername(String username) {
        return jpaQueryFactory
                .select(user)
                .from(user)
                .where(user.username.eq(username))
                .fetchOne();
    }

    @Override
    public User findByNickname(String nickname) {
        return jpaQueryFactory
                .select(user)
                .from(user)
                .where(user.nickname.eq(nickname))
                .fetchOne();
    }

    @Override
    public void softDeleteUserByUsername(String username) {

        LocalDateTime deactivatedAt = LocalDateTime.now();

        jpaQueryFactory
                .update(user)
                .set(user.deactivatedAt, deactivatedAt)
                .where(user.username.eq(username))
                .execute();

    }

    @Override
    public Boolean existsByNickname(String nickname) {
        return jpaQueryFactory
                .selectOne()
                .from(user)
                .where(user.nickname.eq(nickname))
                .fetchFirst() != null;
    }

    @Override
    public List<User> getAllUsersInProgress() {
        return jpaQueryFactory
                .select(user)
                .from(user)
                .where(user.verified.eq(Verified.IN_PROGRESS))
                .fetch();
    }
}
