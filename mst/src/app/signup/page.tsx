"use client";
import { css } from "@emotion/react";
import BasicTopBar from "@/components/common/TopBar/BasicTopBar";
import SubmitForm from "@/components/common/Form/SubmitForm";
import Button from "@/components/common/Button/Button";
import { ChangeEvent, FormEvent, useState } from "react";

interface SignUpFormData {
  username: string;
  height: number | "";
  weight: number | "";
  age: number | "";
  gender: string;
  activityLevel: number | "";
}

const genderOptions = [
  { id: 1, label: "남" },
  { id: 2, label: "여" },
];

const activityOptions = [
  { id: 1, value: 1, label: "활동량이 적은 편" },
  { id: 2, value: 2, label: "활동량이 적당한 편" },
  { id: 3, value: 3, label: "활동량이 많은 편" },
];

export default function SignUp() {
  const [signUpFormData, setSignUpFormData] = useState<SignUpFormData>({
    username: "",
    height: "",
    weight: "",
    age: "",
    gender: "",
    activityLevel: "",
  });

  const [selectedGender, setSelectedGender] = useState<string | null>(null);
  const [selectedActivity, setSelectedActivity] = useState<number | null>(null);

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { id, value } = e.target;
    setSignUpFormData((prev) => ({
      ...prev,
      [id]: value,
    }));
  };

  const handleGenderChange = (val: string) => {
    setSelectedGender(val);
    setSignUpFormData((prev) => ({
      ...prev,
      gender: val,
    }));
  };

  const handleActivityChange = (val: number) => {
    setSelectedActivity(val);
    setSignUpFormData((prev) => ({
      ...prev,
      activityLevel: val,
    }));
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
  };

  console.log(signUpFormData);

  // console.log(selectedGender)
  return (
    <div css={signUpWrapperCSS}>
      <BasicTopBar content="회원 가입" />
      <form css={signUpFormWrapperCSS}>
        <div css={signUpFormCSS}>
          <SubmitForm
            leftLabel="닉네임"
            id="username"
            value={signUpFormData.username}
            onChange={handleChange}
          />
          <SubmitForm
            position="top"
            leftLabel="키"
            rightLabel="cm"
            type="number"
            id="height"
            value={signUpFormData.height}
            onChange={handleChange}
          />
          <SubmitForm
            position="middle"
            leftLabel="몸무게"
            rightLabel="kg"
            type="number"
            id="weight"
            value={signUpFormData.weight}
            onChange={handleChange}
          />
          <SubmitForm
            position="bottom"
            leftLabel="나이"
            rightLabel="살"
            type="number"
            id="age"
            value={signUpFormData.age}
            onChange={handleChange}
          />
          <div css={genderBtnWrapperCSS}>
            {genderOptions.map((opt) => (
              <div
                key={opt.id}
                css={[
                  genderBtnCSS,
                  selectedGender === opt.label && selectedBtnCSS,
                ]}
                onClick={() => handleGenderChange(opt.label)}
              >
                {opt.label}
              </div>
            ))}
          </div>
          <div css={activityBtnWrapperCSS}>
            {activityOptions.map((opt) => (
              <div
                key={opt.id}
                css={[
                  activityBtnCSS,
                  selectedActivity === opt.value && selectedBtnCSS,
                ]}
                onClick={() => handleActivityChange(opt.value)}
              >
                <div css={activityBtnTextCSS}>{opt.label}</div>
              </div>
            ))}
          </div>
        </div>
      </form>
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

const selectedBtnCSS = css`
  color: var(--default-water-color);
  border: 1px solid var(--default-water-color);
`;
