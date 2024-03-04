"use client";

import React from "react";
import { css } from "@emotion/react";

import type { FormPropsType } from "@/types/formTypes";

function Form({ content, cssProps, shadow }: FormPropsType) {
  return (
    <div css={[formWrapperCSS, cssProps, shadow && shadowCSS]}>
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

const shadowCSS = css`
  box-shadow: 0px 3px 3px 0px rgba(0, 0, 0, 0.2);
`;

export default Form;
