"use client";
import { css } from "@emotion/react";
import BasicTopBar from "@/components/common/TopBar/BasicTopBar";
import SubmitForm from "@/components/common/Form/SubmitForm";
import Button from "@/components/common/Button/Button";

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
          <div css={genderBtnWrapperCSS}>
            <div css={genderBtnCSS}>남</div>
            <div css={genderBtnCSS}>여</div>
          </div>
          <div css={activityBtnWrapperCSS}>
            <div css={activityBtnCSS}>
              <div css={activityBtnTextCSS}>활동량이 적은 편</div>
            </div>
            <div css={activityBtnCSS}>
              <div css={activityBtnTextCSS}>활동량이 적당한 편</div>
            </div>
            <div css={activityBtnCSS}>
              <div css={activityBtnTextCSS}>활동량이 많은 편</div>
            </div>
          </div>
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
  display: flex;
  justify-content: center;
  align-items: center;
`;

const signUpFormCSS = css`
  width: 100%;
`;

const genderBtnWrapperCSS = css`
  margin-bottom: 56px;
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 50px;
`;

const activityBtnWrapperCSS = css`
  display: flex;
  align-items: center;
  gap: 10px;
`;

const defaultSignUpFormBtnCSS = css`
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: var(--default-white-color);
  border-radius: 15px;
  color: var(--gray-color-4);
  border: 1px solid var(--gray-color-4);
`;

const genderBtnCSS = css`
  ${defaultSignUpFormBtnCSS}
  height: 40px;
`;

const activityBtnCSS = css`
  ${defaultSignUpFormBtnCSS}
  height: 85px;
  text-align: center;
  line-height: 23px;
`;

const activityBtnTextCSS = css`
  min-width: 70px;
  max-width: 80px;
`;
