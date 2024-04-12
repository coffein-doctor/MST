"use client";
import { css } from "@emotion/react";
import Image from "next/image";
import KakaoLoginBtn from "@/assets/png/kakao_login_large_wide.png";

export default function Login() {
  return (
    <div css={loginWrapperCSS}>
      <div css={titleLogoWrapperCSS}>
        <div>로고</div>
      </div>
      <div css={logoCharacterWrapperCSS}>
        <div>캐릭터</div>
      </div>
      <div css={loginBtnWrapperCSS}>
        <Image
          css={loginBtnCSS}
          src={KakaoLoginBtn}
          alt="카카오버튼"
          priority
        />
      </div>
    </div>
  );
}

const loginWrapperCSS = css`
  display: flex;
  flex-direction: column;
  height: 100vh;
`;

const titleLogoWrapperCSS = css`
  height: 64px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const logoCharacterWrapperCSS = css`
  flex-grow: 1;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const loginBtnWrapperCSS = css`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 55px;
  margin: 20px;
`;

const loginBtnCSS = css`
  width: 100%;
  height: auto;
`;
