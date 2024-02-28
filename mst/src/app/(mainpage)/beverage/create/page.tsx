"use client";
import React from "react";
import { css } from "@emotion/react";
import TopBar from "@/components/common/TopBar/TopBar";

function BeverageCreate() {
  return (
    <div>
      <TopBar content="음료 등록" />
      <form>
        {/* 이름/제조사 */}
        <div css={formSetUpWrapperCSS}>
          <div css={beverageFormLeftLabelWrapperCSS}>
            <label css={beverageFormLeftLabelCSS}>이름</label>
          </div>
          <input css={beverageLeftContentCSS} type="text" autoFocus />
        </div>
        <div css={formSetBottomWrapperCSS}>
          <div css={beverageFormLeftLabelWrapperCSS}>
            <label css={beverageFormLeftLabelCSS}>제조사</label>
          </div>
          <input css={beverageLeftContentCSS} type="text" />
        </div>
        {/* 카페인/당/섭취량 */}
        <div css={formSetUpWrapperCSS}>
          <div css={beverageFormLeftLabelWrapperCSS}>
            <label css={beverageFormLeftLabelCSS}>카페인</label>
          </div>
          <input css={beverageRightContentCSS} type="text" />
          <div css={beverageFormRightLabelWrapperCSS}>
            <label css={beverageFormRightLabelCSS}>mg</label>
          </div>
        </div>
        <div css={formSetMiddleWrapperCSS}>
          <div css={beverageFormLeftLabelWrapperCSS}>
            <label css={beverageFormLeftLabelCSS}>당</label>
          </div>
          <input css={beverageRightContentCSS} type="text" />
          <div css={beverageFormRightLabelWrapperCSS}>
            <label css={beverageFormRightLabelCSS}>g</label>
          </div>
        </div>
        <div css={formSetBottomWrapperCSS}>
          <div css={beverageFormLeftLabelWrapperCSS}>
            <label css={beverageFormLeftLabelCSS}>섭취량</label>
          </div>
          <input css={beverageRightContentCSS} type="text" />
          <div css={beverageFormRightLabelWrapperCSS}>
            <label css={beverageFormRightLabelCSS}>ml</label>
          </div>
        </div>
      </form>
    </div>
  );
}

const formSetUpWrapperCSS = css`
  height: 45px;
  border: solid 1px var(--gray-color-4);
  border-radius: 15px 15px 0px 0px;
  background-color: white;
  display: flex;
  padding: 0px 20px;
  margin-top: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const formSetMiddleWrapperCSS = css`
  height: 45px;
  border: solid 1px var(--gray-color-4);
  border-top: none;
  background-color: white;
  display: flex;
  padding: 0px 20px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const formSetBottomWrapperCSS = css`
  height: 45px;
  border: solid 1px var(--gray-color-4);
  border-top: none;
  border-radius: 0px 0px 15px 15px;
  background-color: white;
  display: flex;
  padding: 0px 20px;
  margin-bottom: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const beverageLeftContentCSS = css`
  flex: 1 0 auto;
  border: none;
  outline: none;
  background-color: transparent;
  font-size: var(--font-size-h5);
  padding-bottom: 3px;
`;

const beverageRightContentCSS = css`
  flex: 1 0 auto;
  border: none;
  outline: none;
  background-color: transparent;
  text-align: right;
  font-size: var(--font-size-h5);
  padding-bottom: 3px;
`;

const beverageFormLeftLabelWrapperCSS = css`
  width: 60px;
`;

const beverageFormRightLabelWrapperCSS = css`
  width: 30px;
`;

const beverageFormLeftLabelCSS = css`
  color: var(--gray-color-4);
  font-size: var(--font-size-h5);
  margin-right: 10px;
`;

const beverageFormRightLabelCSS = css`
  color: var(--gray-color-4);
  font-size: var(--font-size-h5);
  margin-left: 5px;
`;

export default BeverageCreate;
