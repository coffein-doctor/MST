import { css } from "@emotion/react";
import React from "react";
import Input from "./Input";
import { SubmitFormPropsType } from "@/types/formTypes";

// default, 상, 중, 하 position 모양 지정
// label(L/R) 유무 지정
// 내부에 input
function SubmitFormWrapper({
  id,
  position,
  leftLabel,
  rightLabel,
  value,
  onChange,
  type,
	placeholder,
}: SubmitFormPropsType) {
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
      wrapperPos = formSetWrapperCSS;
  }

  return (
    <div css={wrapperPos}>
      {leftLabel && (
        <label htmlFor={id || ""} css={inputLabelLeftWrapperCSS}>
          {leftLabel}
        </label>
      )}
      <Input
        id={id}
        name={id}
        type={type}
        value={value}
        onChange={onChange}
        // && 연산자로 수정이 안됨
        cssProps={
          leftLabel && rightLabel ? css({ textAlign: "right" }) : undefined
        }
      />

      {rightLabel && (
        <label htmlFor={id || ""} css={inputLabelRightWrapperCSS}>
          {rightLabel}
        </label>
      )}
    </div>
  );
}

const formSetWrapperCSS = css`
  height: 45px;
  border: solid 1px var(--gray-color-4);
  background-color: var(--default-white-color);
  display: flex;
  padding: 0px 20px;
  justify-content: center;
  align-items: center;
  border-radius: 15px;
`;

const formSetTopWrapperCSS = css`
  ${formSetWrapperCSS}
  border-radius: 15px 15px 0px 0px;
  margin-top: 10px;
`;

const formSetMiddleWrapperCSS = css`
  ${formSetWrapperCSS}
  border-radius: 0px;
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
  margin-right: 5px;
`;

const inputLabelRightWrapperCSS = css`
  width: 30px;
  color: var(--gray-color-4);
  font-size: var(--font-size-h5);
  margin-left: 5px;
`;

export default SubmitFormWrapper;
