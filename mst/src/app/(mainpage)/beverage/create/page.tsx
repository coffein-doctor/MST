"use client";
import React, { ChangeEvent, useState } from "react";
import { css } from "@emotion/react";
import TopBar from "@/components/common/TopBar/TopBar";
import SubmitFormWrapper from "@/components/Beverage/SubmitFormWrapper";
import RatingForm from "@/components/Beverage/RatingForm";
import DatePicker from "@/components/Beverage/DatePicker/CustomDatePicker";

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
      <TopBar content="음료 등록" />
      <form>
        {/* 이름/제조사 */}
        <SubmitFormWrapper position="top" leftLabel="이름">
          <input
            value={formData.name}
            onChange={handleInputChange}
            css={beverageLeftContentCSS}
            type="text"
            id="name"
            name="name"
            autoComplete="off"
            autoFocus
          />
        </SubmitFormWrapper>
        <SubmitFormWrapper position="bottom" leftLabel="제조사">
          <input
            value={formData.manufacturer}
            onChange={handleInputChange}
            css={beverageLeftContentCSS}
            type="text"
            id="manufacturer"
            name="manufacturer"
          />
        </SubmitFormWrapper>
        {/* 카페인/당/섭취량 */}
        <SubmitFormWrapper position="top" leftLabel="카페인" rightLabel="mg">
          <input
            value={formData.caffeine}
            onChange={handleInputChange}
            css={beverageRightContentCSS}
            type="number"
            id="caffeine"
            name="caffeine"
          />
        </SubmitFormWrapper>
        <SubmitFormWrapper position="middle" leftLabel="당" rightLabel="g">
          <input
            value={formData.sugar}
            onChange={handleInputChange}
            css={beverageRightContentCSS}
            type="number"
            id="sugar"
            name="sugar"
          />
        </SubmitFormWrapper>
        <SubmitFormWrapper position="bottom" leftLabel="섭취량" rightLabel="ml">
          <input
            value={formData.intake}
            onChange={handleInputChange}
            css={beverageRightContentCSS}
            type="number"
            id="intake"
            name="intake"
          />
        </SubmitFormWrapper>
        {/* 날짜/시간 */}
        <DatePicker/>
        {/* 평가 */}
        <RatingForm
          ratingValue={ratingData.ratingValue}
          ratingText={ratingData.ratingText}
          onRatingChange={handleRatingChange}
        />
      </form>
    </div>
  );
}

// inputContentCSS
const beverageContentCSS = css`
  flex: 1 0 auto;
  border: none;
  outline: none;
  background-color: transparent;
  font-size: var(--font-size-h5);
  padding-bottom: 3px;
`;

const beverageLeftContentCSS = css`
  ${beverageContentCSS}
`;

const beverageRightContentCSS = css`
  ${beverageContentCSS}
  text-align: right;
`;

//datePicker

export default BeverageCreate;
