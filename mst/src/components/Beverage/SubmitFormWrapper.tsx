import { css } from "@emotion/react";
import React from "react";
interface SubmitFormProps {
  children: React.ReactNode;
  position: "top" | "middle" | "bottom";
  leftLabel?: string
  rightLabel?: string
}
// 상, 중, 하 position 모양 지정
// label(L/R) 유무 지정
// 내부에 input
function SubmitFormWrapper({
  children,
  position,
  leftLabel,
  rightLabel,
}: SubmitFormProps) {
  let wrapperPos;

  switch (position) {
    case "top":
      wrapperPos = formSetTopWrapperCSS;
      break;
    case "middle":
      wrapperPos = formSetMiddleWrapperCSS;
      break;
    case "bottom":
      wrapperPos = formSetBottomWrapperCSS;
      break;
    default:
      wrapperPos = formSetTopWrapperCSS;
  }

  return (
    <div css={wrapperPos}>
      {leftLabel && <div css={inputLabelLeftWrapperCSS}>{leftLabel}</div>}
      {children}
      {rightLabel && <div css={inputLabelRightWrapperCSS}>{rightLabel}</div>}
    </div>
  );
}

const formSetTopWrapperCSS = css`
  height: 45px;
  border: solid 1px var(--gray-color-4);
  border-radius: 15px 15px 0px 0px;
  background-color: white;
  display: flex;
  padding: 0px 20px;
  margin-top: 10px;
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
  justify-content: center;
  align-items: center;
`;

const inputLabelLeftWrapperCSS = css`
  width: 60px;
  color: var(--gray-color-4);
  font-size: var(--font-size-h5);
  margin-right: 10px;
`;

const inputLabelRightWrapperCSS = css`
  width: 30px;
  color: var(--gray-color-4);
  font-size: var(--font-size-h5);
  margin-left: 5px;
`;

export default SubmitFormWrapper;
