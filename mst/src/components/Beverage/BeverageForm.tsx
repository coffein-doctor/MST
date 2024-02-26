import { EMPTYHEART, FULLHEART } from "@/assets/icons";
import { css } from "@emotion/react";

interface BeverageContentProps {
  registNum: number;
  name: string;
  company: string;
  liked: boolean;
}

function BeverageForm({
  registNum,
  name,
  company,
  liked,
}: BeverageContentProps) {
  const calculateRegistInfo = (num: number) => {
    let pillColor, registNumText;

    if (num >= 1000) {
      pillColor = "var(--default-red-color)";
      registNumText = "1000회 이상";
    } else if (num >= 100) {
      pillColor = "var(--default-yellow-color)";
      registNumText = "100회 이상";
    } else if (num >= 50) {
      pillColor = "var(--default-water-color)";
      registNumText = "50회 이상";
    } else {
      pillColor = "var(--gray-color-3)";
      registNumText = "50회 미만";
    }
    return { pillColor, registNumText };
  };

  const { pillColor, registNumText } = calculateRegistInfo(registNum);

  const registNumPillWrapperCSS = css`
    padding: 3px 8px;
    border-radius: 20px;
    margin-bottom: 10px;
    color: white;
    background-color: ${pillColor};
  `;
  return (
    <div>
      <div css={beverageContentWrapperCSS}>
        <div css={registNumPillWrapperCSS}>
          <div css={registNumPillContentCSS}>{registNumText}</div>
        </div>
        <div>{liked ? FULLHEART : EMPTYHEART}</div>
      </div>
      <div css={beverageNameCSS}>{name}</div>
      <div css={beverageCompanyCSS}>{company}</div>
    </div>
  );
}

const beverageContentWrapperCSS = css`
  display: flex;
  justify-content: space-between;
`;

const registNumPillContentCSS = css`
  font-size: var(--font-size-h6);
  font-weight: var(--font-weight-semibold);
`;

const beverageNameCSS = css`
  margin-bottom: 5px;
  font-size: var(--font-size-h);
  font-weight: var(--font-weight-semibold);
`;

const beverageCompanyCSS = css`
  font-size: var(--font-size-h6);
`;

export default BeverageForm;
