"use client";
import React from "react";
import { css } from "@emotion/react";
import TopBar from "@/components/common/TopBar/TopBar";
import SubmitFormWrapper from "@/components/Beverage/SubmitFormWrapper";
import RatingForm from "@/components/Beverage/RatingForm";

function BeverageCreate() {
  return (
    <div>
      <TopBar content="음료 등록" />
      <form>
        {/* 이름/제조사 */}
        <SubmitFormWrapper position="top" leftLabel="이름" inputAlign="right" />

        <SubmitFormWrapper position="bottom" leftLabel="제조사" />

        {/* 카페인/당/섭취량 */}
        <SubmitFormWrapper
          position="top"
          leftLabel="카페인"
          rightLabel="mg"
          inputAlign="right"
        />

        <SubmitFormWrapper
          position="middle"
          leftLabel="당"
          rightLabel="g"
          inputAlign="right"
        />

        <SubmitFormWrapper
          position="bottom"
          leftLabel="섭취량"
          rightLabel="ml"
          inputAlign="right"
        />

        {/* 날짜/시간 */}
        <div>time</div>
        {/* 평가 */}
        <RatingForm />
      </form>
    </div>
  );
}


//datePicker

export default BeverageCreate;
