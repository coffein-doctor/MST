import { css } from "@emotion/react";
export default function BasicHR() {
  return <hr css={hrCSS} />;
}
const hrCSS = css`
  color: var(--gray-color-4);
  margin-bottom: 30px;
`;
