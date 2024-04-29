"use client";
import { css } from "@emotion/react";
import BasicTopBar from "@/components/common/TopBar/BasicTopBar";
import SubmitForm from "@/components/common/Form/SubmitForm";
import Button from "@/components/common/Button/Button";
import { ChangeEvent, FormEvent, useState } from "react";
import CustomDatePicker from "@/components/Beverage/DatePicker/CustomDatePicker";
import dayjs, { Dayjs } from "dayjs";
import { SignUpFormData } from "@/types/api/apiRequestTypes";
import { postSignUpAPI } from "@/api/user/postSignUpAPI";
import { useRouter } from "next/navigation";
import { ErrorStateType, ValidationConfigType } from "@/types/validationTypes";
import validateFormData from "@/utils/validateFormData";

const genderOptions = [
  { id: 1, label: "남", value: "MALE" },
  { id: 2, label: "여", value: "FEMALE" },
];

const activityOptions = [
  { id: 1, value: "LOW", label: "활동량이 적은 편" },
  { id: 2, value: "MEDIUM", label: "활동량이 적당한 편" },
  { id: 3, value: "HIGH", label: "활동량이 많은 편" },
];

const initialSignUpFormData: SignUpFormData = {
  nickname: "",
  birth: "",
  gender: "",
  activityLevel: "",
  height: "",
  weight: "",
  introduction: "",
};

const initialErrorState: ErrorStateType = {
  nickname: "",
  birth: "",
  gender: "",
  activityLevel: "",
  height: "",
  weight: "",
};

const signUpValidationConfig: Record<string, ValidationConfigType> = {
  nickname: {
    required: true,
    maxLength: 8,
    errorMessage: "닉네임은 최대 8자까지만 입력할 수 있습니다.",
  },
  height: {
    required: true,
    errorMessage: "키를 입력해주세요.",
  },
  weight: {
    required: true,
    errorMessage: "몸무게를 입력해주세요.",
  },
  birth: {
    required: true,
    errorMessage: "생일을 입력해주세요.",
  },
  gender: {
    required: true,
    errorMessage: "성별을 선택해주세요.",
  },
  activityLevel: {
    required: true,
    errorMessage: "활동량을 선택해주세요.",
  },
};

export default function SignUp() {
  const router = useRouter();
  const [signUpFormData, setSignUpFormData] = useState<SignUpFormData>(
    initialSignUpFormData
  );
  const [selectedGender, setSelectedGender] = useState<string | null>(null);
  const [selectedActivity, setSelectedActivity] = useState<string | null>(null);
  const [selectedDate, setSelectedDate] = useState<Dayjs | null>(dayjs());
  // ERROR
  const [error, setError] = useState(initialErrorState);
  const [errorMessage, setErrorMessage] = useState("");

  const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { id, value } = e.target;
    setSignUpFormData((prev) => ({
      ...prev,
      [id]: value,
    }));
    setError((prev) => ({
      ...prev,
      [id]: "",
    }));
    setErrorMessage("");
  };

  const handleSelectChange = (key: string, val: string) => {
    if (key === "gender") {
      setSelectedGender(val);
    }
    if (key === "activityLevel") {
      setSelectedActivity(val);
    }
    setSignUpFormData((prev) => ({
      ...prev,
      [key]: val,
    }));
    setError((prev) => ({ ...prev, [key]: "" }));
    setErrorMessage("");
  };

  const handleDateChange = (date: Dayjs | null) => {
    setSelectedDate(date);
    let newDate = dayjs(date).format("YYYY-MM-DD");
    setSignUpFormData((prev) => ({
      ...prev,
      birth: newDate,
    }));
    setError((prev) => ({ ...prev, birth: "" }));
    setErrorMessage("");
  };

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    // 에러체크
    const checkErrorOrder: string[] = [
      "nickname",
      "height",
      "weight",
      "birth",
      "gender",
      "activityLevel",
    ];

    const error: ErrorStateType = validateFormData(
      // 검증config
      signUpValidationConfig,
      // data
      signUpFormData,
      // 검증의 순서
      checkErrorOrder
    );

    if (error) {
      const [firstErrorKey, firstError] = Object.entries(error)[0];
      setError({ [firstErrorKey]: firstError });
      setErrorMessage(firstError);
      return;
    }

    // API
    try {
      const response = await postSignUpAPI({ body: signUpFormData });
      // 대체 기능 필요(모달 등)
      alert("가입이 완료되었습니다");

      setSignUpFormData({
        nickname: "",
        birth: "",
        gender: "",
        activityLevel: "",
        height: "",
        weight: "",
        introduction: "",
      });
      router.push("/home");
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div css={signUpWrapperCSS}>
      <BasicTopBar content="회원 가입" />
      <form css={signUpFormWrapperCSS}>
        <div css={signUpFormCSS}>
          <SubmitForm
            leftLabel="닉네임"
            id="nickname"
            value={signUpFormData.nickname}
            onChange={handleInputChange}
            error={error.nickname}
          />
          <SubmitForm
            position="top"
            leftLabel="키"
            rightLabel="cm"
            type="number"
            id="height"
            value={signUpFormData.height}
            onChange={handleInputChange}
            error={error.height}
          />
          <SubmitForm
            position="bottom"
            leftLabel="몸무게"
            rightLabel="kg"
            type="number"
            id="weight"
            value={signUpFormData.weight}
            onChange={handleInputChange}
            error={error.weight}
          />
          <CustomDatePicker
            type="birthday"
            value={selectedDate}
            handleDateChange={handleDateChange}
            error={error.birth}
          />
          <div css={genderBtnWrapperCSS}>
            {genderOptions.map((opt) => (
              <div
                key={opt.id}
                css={[
                  genderBtnCSS,
                  selectedGender === opt.value && selectedBtnCSS,
                  error.gender && {
                    border: "1px solid var(--default-red-color)",
                    // color: "var(--default-red-color)",
                  },
                ]}
                onClick={() => handleSelectChange("gender", opt.value)}
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
                  error.activityLevel && {
                    border: "1px solid var(--default-red-color)",
                    color: "var(--default-red-color)",
                  },
                ]}
                onClick={() => handleSelectChange("activityLevel", opt.value)}
              >
                <div css={activityBtnTextCSS}>{opt.label}</div>
              </div>
            ))}
          </div>
          <div css={errorMessageCSS}>{errorMessage}</div>
        </div>
      </form>

      <Button content="가입하기" onClick={handleSubmit} />
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
  color: var(--gray-color-1);
  border: 1px solid var(--gray-color-1);
`;

const errorMessageCSS = css`
  color: var(--default-red-color);
  margin-top: 20px;
  padding-left: 5px;
`;
