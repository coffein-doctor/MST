"use client";
import React from "react";
import { css } from "@emotion/react";
import TopBar from "@/components/common/TopBar/TopBar";
import SubmitFormWrapper from "@/components/Beverage/SubmitFormWrapper";

function BeverageCreate() {
  return (
    <div>
      <TopBar content="음료 등록" />
      <form>
        {/* 이름/제조사 */}
        <SubmitFormWrapper position="top" leftLabel="이름">
          <input css={beverageLeftContentCSS} type="text" autoFocus />
        </SubmitFormWrapper>
        <SubmitFormWrapper position="bottom" leftLabel="제조사">
          <input css={beverageLeftContentCSS} type="text" />
        </SubmitFormWrapper>
        {/* 카페인/당/섭취량 */}
        <SubmitFormWrapper position="top" leftLabel="카페인" rightLabel="mg">
          <input css={beverageRightContentCSS} type="text" />
        </SubmitFormWrapper>
        <SubmitFormWrapper position="middle" leftLabel="당" rightLabel="g">
          <input css={beverageRightContentCSS} type="text" />
        </SubmitFormWrapper>
        <SubmitFormWrapper position="bottom" leftLabel="섭취량" rightLabel="ml">
          <input css={beverageRightContentCSS} type="text" />
        </SubmitFormWrapper>
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

export default BeverageCreate;
