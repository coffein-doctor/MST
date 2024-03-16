"use client";

import React from "react";
import { css } from "@emotion/react";
import { ButtonPropsType } from "@/types/buttonTypes";

function Button({ content, onClick, type }: ButtonPropsType) {
  return (
    <button css={buttonWrapperCSS} type={type} onClick={onClick}>
      <div css={buttonContentCSS}>{content}</div>
    </button>
  );
}


const buttonWrapperCSS = css`
  width: 100%;
  height: 55px;
  margin: 20px 0px;
  background-color: var(--default-yellow-color);
  border-radius: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const buttonContentCSS = css`
  color: white;
  font-size: var(--font-size-h4);
  font-weight: bold;
`;

export default Button;
