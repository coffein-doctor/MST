"use client";
import React, { useState } from "react";
import { css } from "@emotion/react";
import { useRouter } from "next/navigation";

function SelectTopBar({
  options,
}: {
  options: { name: string; value: string }[];
}) {
  const [selectedOption, setSelectedOption] = useState(options[0].value);

  const router = useRouter();

  const handleOptionClick = (value: string) => {
    setSelectedOption(value);
    router.push(`/${value}`);
  };

  return (
    <div css={selectTopBarWrapperCSS}>
      {options.map((option) => (
        <div
          key={option.value}
          css={[
            selectTopBarContentCSS,
            selectedOption === option.value && clickedTopBarContentCSS,
          ]}
          onClick={() => handleOptionClick(option.value)}
        >
          {option.name}
        </div>
      ))}
    </div>
  );
}

const selectTopBarWrapperCSS = css`
  width: 100vw;
  height: 64px;
  display: flex;
  justify-content: start;
  align-items: center;
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
