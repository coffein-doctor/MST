import { css } from "@emotion/react";
import React from "react";
interface SubmitFormProps {
  children: React.ReactNode;
  position: "top" | "middle" | "bottom";
  leftLabel?: string;
  rightLabel?: string;
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
      {leftLabel && <label css={inputLabelLeftWrapperCSS}>{leftLabel}</label>}
      {children}
      {rightLabel && (
        <label css={inputLabelRightWrapperCSS}>{rightLabel}</label>
      )}
    </div>
  );
}

const formSetWrapperCSS = css`
  height: 45px;
  border: solid 1px var(--gray-color-4);
  background-color: white;
  display: flex;
  padding: 0px 20px;
  justify-content: center;
  align-items: center;

  input[type="number"]::-webkit-inner-spin-button,
  input[type="number"]::-webkit-outer-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }
`;

const formSetTopWrapperCSS = css`
  ${formSetWrapperCSS}
  border-radius: 15px 15px 0px 0px;
  margin-top: 10px;
`;

const formSetMiddleWrapperCSS = css`
  ${formSetWrapperCSS}
  border-top: none;
`;

const formSetBottomWrapperCSS = css`
  ${formSetWrapperCSS}
  border-top: none;
  border-radius: 0px 0px 15px 15px;
  margin-bottom: 10px;
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
