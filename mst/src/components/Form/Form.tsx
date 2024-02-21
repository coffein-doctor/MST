"use client";

import React from "react";
import { css } from "@emotion/react";

import type { FormPropsType } from "@/types/formTypes";

function Form({ content }: FormPropsType) {
  return (
    <div css={formWrapperCSS}>
      <div css={formContentWrapperCSS}>{content}</div>
    </div>
  );
}

const formWrapperCSS = css`
  background-color: var(--default-white-color);
  border-radius: 20px;
`;

const formContentWrapperCSS = css`
  padding: 20px;
`;

export default Form;
