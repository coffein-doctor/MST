"use client";
import React, { useState } from "react";
import { css } from "@emotion/react";
import { SelectTopBarProps } from "@/types/topBarTypes";

function SelectTopBar({ firstCategory, secondCategory }: SelectTopBarProps) {
  // 나중에 parentcomponent에서 관리할 때 변경 필요
  const [selectedCategory, setSelectedCategory] = useState(firstCategory);

  const handleCategorySelected = (category: string) => {
    setSelectedCategory(category);
  };

  return (
    <div css={selectTopBarWrapperCSS}>
      <div
        css={[
          selectTopBarContentCSS,
          selectedCategory === firstCategory && clickedTopBarContentCSS,
        ]}
        onClick={() => handleCategorySelected(firstCategory)}
      >
        {firstCategory}
      </div>
      <div
        css={[
          selectTopBarContentCSS,
          selectedCategory === secondCategory && clickedTopBarContentCSS,
        ]}
        onClick={() => handleCategorySelected(secondCategory)}
      >
        {secondCategory}
      </div>
    </div>
  );
}

const selectTopBarWrapperCSS = css`
  width: 100vw;
  height: 64px;
  display: flex;
  justify-content: start;
  align-items: center;
  padding: 0px 20px;
`;

const selectTopBarContentCSS = css`
  color: var(--gray-color-2);
  font-size: var(--font-size-h2);
  font-weight: var(--font-weight-medium);
  margin-right: 10px;
`;

const clickedTopBarContentCSS = css`
  ${selectTopBarContentCSS}
  color: var(--default-yellow-color);
  font-weight: var(--font-weight-bold);
`;

export default SelectTopBar;
