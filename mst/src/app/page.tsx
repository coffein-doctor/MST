"use client";
import { css } from "@emotion/react";
import Image from "next/image";
import KakaoLoginBtn from "@/assets/png/kakao_login_large_wide.png";
import { useRouter, useSearchParams } from "next/navigation";
import TempLogo from "@/assets/png/TempLogo.png";
import Loading from "@/components/common/Loading/Loading";
import { getUserStatusAPI } from "@/api/user/getUserStatusAPI";
import { useEffect } from "react";
import {  setCookie } from "@/api/cookie";

const setRefreshToken = () => {
  const refreshToken =
    "eyJhbGciOiJIUzI1NiJ9.eyJjYXRlZ29yeSI6InJlZnJlc2giLCJ1c2VybmFtZSI6Imtha2FvXzM0MzgxNzUwMjEiLCJyb2xlIjoiUk9MRV9VU0VSIiwiaWF0IjoxNzE0MTI3NDg1LCJleHAiOjE3MTQyMTM4ODV9.i0luMqisDJToGLI-GhFtFtEAxFQGr5XLtttZoq1Dx14";
  setCookie("refresh", refreshToken, {
    path: "/",
    maxAge: 30 * 24 * 60 * 60,
  });
};

export default function Login() {
  const baseUrl = process.env.NEXT_PUBLIC_BASE_URL;
  const router = useRouter();

  const handleLogin = async () => {
    setRefreshToken();
    router.push(`${baseUrl}/oauth2/authorization/kakao`);

    const status = await getUserStatusAPI();

    if (status === "NEW_USER") {
      router.push("/signup");
    } else {
      router.push("/home");
    }
  };

  // 임시 코드
  const searchParams = useSearchParams();

  useEffect(() => {
    // 엑세스 토큰을 params에서 가져옴
    const accessToken = searchParams.get("access");

    const getUserStatus = async () => {
      try {
        const status = await getUserStatusAPI();

        if (status === "NEW_USER") {
          router.push("/signup");
        } else {
          router.push("/home");
        }
      } catch (error) {
        console.log(error);
      }
    };

    if (accessToken) {
      localStorage.setItem("access", accessToken);
      getUserStatus();
    }
  }, [searchParams, router]);


  return (
    <div css={loginWrapperCSS}>
      <div css={titleLogoWrapperCSS}>
        <Image src={TempLogo} alt="logo" priority />
      </div>
      <div css={logoCharacterWrapperCSS}>
        <div css={logoWrapperCSS}>
          <Loading />
        </div>
      </div>
      <div css={loginBtnWrapperCSS}>
        <Image
          onClick={handleLogin}
          css={loginBtnCSS}
          src={KakaoLoginBtn}
          alt="kakao-login"
          priority
        />
      </div>
    </div>
  );
}

const loginWrapperCSS = css`
  display: flex;
  flex-direction: column;
  height: 100%;
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

const logoWrapperCSS = css`
  width: 100%;
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
  z-index: 10;
`;
