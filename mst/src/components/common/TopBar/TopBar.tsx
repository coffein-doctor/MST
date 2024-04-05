"use client";

import React from "react";
import { css } from "@emotion/react";
import { useRouter } from "next/navigation";
import { LEFTARROW } from "@/assets/svgs";
import { TopBarPropsType } from "@/types/topBarTypes";
import { usePathname } from "next/navigation";

function TopBar({ type, content, selectOptions }: TopBarPropsType) {
  const router = useRouter();

  const pathname = usePathname();

  switch (type) {
    case "basic":
      return (
        <div css={topBarWrapperCSS}>
          <div css={backIconWrapperCSS} onClick={() => router.back()}>
            <LEFTARROW color="var(--gray-color-2)" />
          </div>
          <div css={topBarContentCSS}>{content}</div>
        </div>
      );

    case "select":
      return (
        <div css={selectTopBarWrapperCSS}>
          {selectOptions?.map((option) => (
            <div
              key={option.value}
              css={[
                optionItemCSS,
                pathname === option.value && activeOptionCSS,
              ]}
              onClick={() => router.push(option.value)}
            >
              {option.label}
            </div>
          ))}
        </div>
      );

    case "search":
      return <div>search component but not yet</div>;
  }
}

const topBarWrapperCSS = css`
  width: 100vw;
  height: 64px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0px 20px;
`;

const backIconWrapperCSS = css`
  position: absolute;
  left: 20px;
`;

const topBarContentCSS = css`
  color: var(--gray-color-2);
  font-size: var(--font-size-h3);
`;

const selectTopBarWrapperCSS = css`
  ${topBarWrapperCSS};
  justify-content: start;
`;

const optionItemCSS = css`
  margin-right: 10px;
`;

const activeOptionCSS = css`
  color: var(--default-yellow-color);
  font-weight: var(--font-weight-bold);
`;

export default TopBar;
