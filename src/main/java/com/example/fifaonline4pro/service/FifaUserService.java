package com.example.fifaonline4pro.service;

import com.example.fifaonline4pro.domain.FifaUser;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/08
 */
public interface FifaUserService {

    // 닉네임으로 유저 정보 조회
    FifaUser findUserByNickname(String nickname);
    // 유저 고유 식별자로 유저 정보 조회
    FifaUser findUserByAccessId(String accessId);
}