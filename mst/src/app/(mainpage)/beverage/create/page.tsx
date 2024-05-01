"use client";
import React, { ChangeEvent, useState } from "react";
import { css } from "@emotion/react";
import SubmitForm from "@/components/common/Form/SubmitForm";
import CustomTimePicker from "@/components/Beverage/TimePicker/CustomTimePicker";
import CustomDatePicker from "@/components/Beverage/DatePicker/CustomDatePicker";
import RatingForm from "@/components/Beverage/RatingForm";
import Button from "@/components/common/Button/Button";
import BasicHR from "@/components/Beverage/BasicHR";
import BasicTopBar from "@/components/common/TopBar/BasicTopBar";

function BeverageCreate() {
  const [formData, setFormData] = useState({
    name: "",
    manufacturer: "",
    caffeine: "",
    sugar: "",
    intake: "",
  });

  // 추후 hook 분리 예정
  const [ratingData, setRatingData] = useState({
    ratingValue: null,
    ratingText: "",
  });

  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleRatingChange = (ratingValue: number, ratingText: string) => {
    setRatingData((prevData) => ({
      ...prevData,
      [ratingValue]: ratingValue,
      [ratingText]: ratingText,
    }));
  };

  return (
    <div>
			<BasicTopBar content="음료 등록"/>
      <form>
        {/* 이름/제조사 */}
        <SubmitForm
          position="top"
          leftLabel="이름"
          id="name"
          value={formData.name}
          onChange={handleInputChange}
        />
        <SubmitForm
          position="bottom"
          leftLabel="제조사"
          id="manufacturer"
          value={formData.manufacturer}
          onChange={handleInputChange}
        />
        {/* 카페인/당/섭취량 */}
        <SubmitForm
          position="top"
          leftLabel="카페인"
          rightLabel="mg"
          id="caffeine"
          value={formData.caffeine}
          onChange={handleInputChange}
          type="number"
        />
        <SubmitForm
          position="middle"
          leftLabel="당"
          rightLabel="g"
          id="sugar"
          value={formData.sugar}
          onChange={handleInputChange}
          type="number"
        />
        <SubmitForm
          position="bottom"
          leftLabel="섭취량"
          rightLabel="ml"
          id="intake"
          value={formData.intake}
          onChange={handleInputChange}
          type="number"
        />
        {/* 날짜/시간 */}
        <div css={timeDatePickerWrapperCSS}>
          <CustomDatePicker />
          <CustomTimePicker />
        </div>
        <BasicHR />
        {/* 평가 */}
        <RatingForm
          ratingValue={ratingData.ratingValue}
          ratingText={ratingData.ratingText}
          onRatingChange={handleRatingChange}
        />
        <Button content="추가하기" />
      </form>
    </div>
  );
}

//datePicker
const timeDatePickerWrapperCSS = css`
  margin: 20px 0px;
`;

export default BeverageCreate;
