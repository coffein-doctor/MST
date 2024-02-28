import { css } from "@emotion/react";
interface SubmitFormProps {
  children: string;
  position: string;
  label: string;
  labelPosition: string;
}
function SubmitFormWrapper({
  children,
  position,
  label,
  labelPosition,
}: SubmitFormProps) {
  let wrapperPos;
  switch (position) {
    case "up":
      wrapperPos = formSetUpWrapperCSS;
      break;
    case "middle":
      wrapperPos = formSetMiddleWrapperCSS;
      break;
    case "bottom":
      wrapperPos = formSetBottomWrapperCSS;
      break;
    default:
      wrapperPos = formSetUpWrapperCSS;
  }
  const labelWrapperStyle =
    labelPosition === "left"
      ? inputLabelLeftWrapperCSS
      : inputLabelRightWrapperCSS;

  return (
    <div>
      {label && <div css={labelWrapperStyle}>{label}</div>}
      {children}
    </div>
  );
}

const formSetUpWrapperCSS = css`
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
`;

const inputLabelRightWrapperCSS = css`
  width: 30px;
`;

export default SubmitFormWrapper;
