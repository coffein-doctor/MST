import { EMPTYHEART, FULLHEART } from "@/assets/svgs";
import { css } from "@emotion/react";
import { useState } from "react";
import Form from "../common/Form/Form";

interface BeverageContentProps {
  id: number;
  registNum: number;
  name: string;
  company: string;
  liked: boolean;
  toggleLiked: () => void;
  toBeverageDetail: (id: number) => void;
}

function BeverageForm({
  id,
  registNum,
  name,
  company,
  liked,
  toggleLiked,
  toBeverageDetail,
}: BeverageContentProps) {
  const [isLiked, setIsLiked] = useState(liked);

  const handleToggleLiked = () => {
    // 현재 상태만 변환(empty/full) -> 필요없는 기능
    // setIsLiked(!isLiked);

    // 상위에서 데이터 업데이트
    toggleLiked();
  };

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
    <>
      <Form
        onClick={() => toBeverageDetail(id)}
        cssProps={favBevWrapperCSS}
        shadow
        content={
          <div>
            <div css={beverageContentWrapperCSS}>
              <div css={registNumPillWrapperCSS}>
                <div css={registNumPillContentCSS}>{registNumText}</div>
              </div>
              <div onClick={handleToggleLiked}>
                {isLiked ? FULLHEART : EMPTYHEART}
              </div>
            </div>
            <div css={beverageNameCSS}>{name}</div>
            <div css={beverageCompanyCSS}>{company}</div>
          </div>
        }
      />
    </>
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

const favBevWrapperCSS = css`
  margin-bottom: 13px;
`;
export default BeverageForm;
