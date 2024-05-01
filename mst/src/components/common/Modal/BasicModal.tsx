import React from "react";
import Button from "../Button/Button";
import { css } from "@emotion/react";
import { ModalPropsType } from "@/types/modalTypes";

export default function BasicModal({ content, onClick }: ModalPropsType) {
  return (
    <div css={modalBackGroundCSS}>
      <div css={modalWrapperCSS}>
        <div css={modalContentCSS}>{content}</div>
        <Button content="확인" onClick={onClick} />
      </div>
    </div>
  );
}

const modalBackGroundCSS = css`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
`;

const modalWrapperCSS = css`
  width: calc(100% - 40px);
  height: 180px;
  border-radius: 20px;
  padding: 0px 20px;
  background-color: var(--default-white-color);
  display: flex;
  flex-direction: column;
`;

const modalContentCSS = css`
  flex-grow: 1;
  font-size: var(--font-size-h5);
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
`;
