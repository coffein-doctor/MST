"use client";
import { css } from "@emotion/react";
import Image from "next/image";
import KakaoLoginBtn from "@/assets/png/kakao_login_large_wide.png";
import { useRouter } from "next/navigation";
import TempLogo from "@/assets/png/TempLogo.png";
import Loading from "@/components/common/Loading/Loading";
import { getUserStatusAPI } from "@/api/user/getUserStatusAPI";
import { useEffect } from "react";
import { getCookie } from "@/api/cookie";

export default function Login() {
  const router = useRouter();

  const handleLogin = async () => {
    router.push(`/user-service/oauth2/authorization/kakao`);

    const status = await getUserStatusAPI();
		
    if (status === "NEW_USER") {
      router.push("/signup");
    } else {
      router.push("/home");
    }
  };

  useEffect(() => {
    const accessToken = getCookie("access");
    if (accessToken) {
      const getUserStatus = async () => {
        const status = await getUserStatusAPI();
        if (status === "NEW_USER") {
          router.push("/signup");
        } else {
          router.push("/home");
        }
      };
      getUserStatus();
    }
  }, [router]);

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
