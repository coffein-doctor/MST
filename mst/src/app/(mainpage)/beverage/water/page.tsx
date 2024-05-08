"use client";
import { css } from "@emotion/react";
import Button from "@/components/common/Button/Button";
import CustomDatePicker from "@/components/Beverage/DatePicker/CustomDatePicker";
import CustomTimePicker from "@/components/Beverage/TimePicker/CustomTimePicker";
import SubmitForm from "@/components/common/Form/SubmitForm";
import { ChangeEvent, useEffect, useState } from "react";
import WaterAmountButtons from "@/components/Beverage/WaterAmountButtons";
import BasicTopBar from "@/components/common/TopBar/BasicTopBar";
import dayjs, { Dayjs } from "dayjs";
import { ErrorStateType, ValidationConfigType } from "@/types/validationTypes";
import validateFormData from "@/utils/validateFormData";

interface WaterFormData {
  userId: number | "";
  amount: number;
  createdDate: string;
  createdTime: string;
}

const waterValidationConfig: Record<string, ValidationConfigType> = {
  // useId: {
  //   required: true,
  //   emptyMessage: "유저아이디가 없습니다",
  // },
  amount: {
    required: true,
    minValue: 0,
    errorMessage: "1 이상의 섭취량을 입력해주세요",
  },
  createdDate: {
    required: true,
    emptyMessage: "섭취한 일자를 입력해주세요.",
  },
  createdTime: {
    required: true,
    emptyMessage: "섭취한 시간을 입력해주세요.",
  },
};

const initialWaterFormData: WaterFormData = {
  userId: "",
  amount: 0,
  createdDate: "",
  createdTime: "",
};

// 임시
const tempUserId = 1;

function WaterCreate() {
  const [waterFormData, setWaterFormData] = useState(initialWaterFormData);
  const [waterAmount, setWaterAmount] = useState(0);
  const [selectedDate, setSelectedDate] = useState<Dayjs | null>(dayjs());
  const [selectedTime, setSelectedTime] = useState<Dayjs | null>(dayjs());
  const [error, setError] = useState<ErrorStateType>();
  const [errorMessage, setErrorMessage] = useState("");

  // btn 색깔 관리, btn값 formdata 반영
  const handleWaterAmountBtnClick = (val: number) => {
    setWaterAmount(val);
    setWaterFormData((prev) => ({
      ...prev,
      amount: val,
    }));
  };

  // waterAmount관리
  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const newValue = parseInt(e.target.value);
    setWaterAmount(newValue);
    setWaterFormData((prev) => ({
      ...prev,
      amount: newValue,
    }));
  };

  // DATE
  const handleDateChange = (date: Dayjs | null) => {
    setSelectedDate(date);
    let newDate = dayjs(date).format("YYYY-MM-DD");
    setWaterFormData((prev) => ({
      ...prev,
      createdDate: newDate,
    }));
  };

  // TIME
  const handleTimeChange = (time: Dayjs | null) => {
    setSelectedTime(time);
    let newTime = dayjs(time)
      .format("A hh:mm")
      .replace("AM", "오전")
      .replace("PM", "오후");

    setWaterFormData((prev) => ({
      ...prev,
      createdTime: newTime,
    }));
  };

  // WaterForm 제출
  const handelSubmit = (e: SubmitEvent) => {
    e.preventDefault();

    // ERROR 검증
    const checkErrorOrder: string[] = [
      // "userId",
      "amount",
      "createdDate",
      "createdTime",
    ];

    const formError: ErrorStateType = validateFormData(
      waterValidationConfig,
      waterFormData,
      checkErrorOrder
    );

    if (Object.keys(formError).length > 0) {
      const [firstErrorKey, firstError] = Object.entries(formError)[0];
      setError((prev) => ({
        ...prev,
        [firstErrorKey]: firstError,
      }));
      setErrorMessage(firstError);
      return;
    }

    // API
    try {
      // 요청
      setWaterFormData(initialWaterFormData);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    // userId 요청
    setWaterFormData((prev) => ({
      ...prev,
      userId: tempUserId,
    }));
  }, []);

  return (
    <div>
      <BasicTopBar content="물" />
      <form>
        <div css={waterAmountWrapperCSS}>
          <div css={waterContentTitleCSS}>섭취량</div>
          {/* 상단버튼 */}
          <WaterAmountButtons
            waterAmount={waterAmount}
            onSelect={handleWaterAmountBtnClick}
          />
          {/* 직접입력 */}
          <SubmitForm
            leftLabel="섭취량"
            rightLabel="ml"
            id="water-amount"
            value={waterAmount}
            onChange={handleChange}
            type="number"
          />
        </div>
        <div css={timeDatePickerWrapperCSS}>
          <div css={waterContentTitleCSS}>섭취시간</div>
          <div>
            <CustomDatePicker
              value={selectedDate}
              handleDateChange={handleDateChange}
            />
            <CustomTimePicker
              value={selectedTime}
              handleTimeChange={handleTimeChange}
            />
          </div>
        </div>
        <div>{errorMessage}</div>
        <Button content="추가하기" onClick={handelSubmit} />
      </form>
    </div>
  );
}
export default WaterCreate;

const waterAmountWrapperCSS = css`
  margin-bottom: 75px;
`;
const waterContentTitleCSS = css`
  font-size: var(--font-size-h3);
  font-weight: var(--font-weight-bold);
  text-align: center;
  margin-bottom: 40px;
`;

const timeDatePickerWrapperCSS = css`
  margin-bottom: 103px;
`;
