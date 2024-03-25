"use client";
import React from "react";
import { css } from "@emotion/react";
import { useRouter } from "next/navigation";
import { LEFTARROW } from "@/assets/icons";
import { BasicTopBarProps } from "@/types/topBarTypes";

function BasicTopBar({ content }: BasicTopBarProps) {
  const router = useRouter();
  return (
    <div css={topBarWrapperCSS}>
      <div css={backIconWrapperCSS} onClick={() => router.back()}>
        <LEFTARROW color="var(--gray-color-2)" />
      </div>
      <div css={topBarContentCSS}>{content}</div>
    </div>
  );
}

const topBarWrapperCSS = css`
  width: 100%;
  height: 64px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const backIconWrapperCSS = css`
  position: absolute;
  left: 20px;
`;

const topBarContentCSS = css`
  color: var(--gray-color-2);
  font-size: var(--font-size-h3);
`;

export default BasicTopBar;
