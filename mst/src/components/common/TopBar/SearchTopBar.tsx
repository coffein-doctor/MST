"use client";
import React from "react";
import { css } from "@emotion/react";
import { LEFTARROW, SEARCH } from "@/assets/svgs";
import { useRouter } from "next/navigation";
import BasicInput from "@/components/common/Form/BasicInput";

function SearchTopBar() {
  const router = useRouter();
  return (
    <div css={topBarWrapperCSS}>
      <div css={backIconWrapperCSS} onClick={() => router.back()}>
        <LEFTARROW color="var(--gray-color-4)" />
      </div>
      <div css={searchBarWrapperCSS}>
        <div css={searchIconWrapperCSS}>
          <div>{SEARCH}</div>
        </div>
        <BasicInput
          id="search"
          name="search"
          type="text"
          placeholder="검색어를 입력해주세요"
        />
      </div>
    </div>
  );
}
const topBarWrapperCSS = css`
  width: 100vw;
  height: 64px;
  background-color: var(--gray-color-2);
  position: fixed;
  top: 0px;
  left: 0px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0px 20px;
`;

const backIconWrapperCSS = css`
  margin-right: 40px;
`;

const searchBarWrapperCSS = css`
  flex: 1 0 auto;
  height: 40px;
  background-color: #e7e7e7;
  border-radius: 30px;
  padding: 0px 15px;
  display: flex;
  align-items: center;
`;

const searchIconWrapperCSS = css`
  margin-right: 5px;
  height: 19px;
`;


export default SearchTopBar;
