"use client";
import { css } from "@emotion/react";

import Button from "@/components/common/Button/Button";
import BasicTopBar from "@/components/common/TopBar/BasicTopBar";
import SubmitForm from "@/components/common/Form/SubmitForm";

export default function SignUp() {
  return (
    <div css={signUpWrapperCSS}>
      <BasicTopBar content="회원 가입" />
      <div css={signUpFormWrapperCSS}>
        <div css={signUpFormCSS}>
          <SubmitForm leftLabel="닉네임" id="username" />
          <SubmitForm
            position="top"
            leftLabel="키"
            rightLabel="cm"
            id="height"
          />
          <SubmitForm
            position="middle"
            leftLabel="몸무게"
            rightLabel="kg"
            id="weight"
          />
          <SubmitForm
            position="bottom"
            leftLabel="나이"
            rightLabel="살"
            id="age"
          />
					
        </div>
      </div>
      <Button content="가입하기" />
    </div>
  );
}

const signUpWrapperCSS = css`
  display: flex;
  flex-direction: column;
  height: 100vh;
`;

const signUpFormWrapperCSS = css`
  flex-grow: 1;
  background-color: gray;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const signUpFormCSS = css`
  width: 100%;
`;
