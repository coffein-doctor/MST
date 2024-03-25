import useGetRatingColor from "@/utils/useGetRatingColor";
import { css } from "@emotion/react";

interface FeedContentProps {
  beverageName: string;
  manufacturer: string;
  ratingNum: number;
  userName: string;
  content: string;
}

export default function FeedForm({
  beverageName,
  manufacturer,
  ratingNum,
  userName,
  content,
}: FeedContentProps) {
  9;
  const getRatingCircleColor = useGetRatingColor();

  return (
    <div css={articleFormWrapperCSS}>
      <div css={articleRateWrapperCSS}>
        <div css={[articleRateCircleCSS, getRatingCircleColor(ratingNum)]}>
          {ratingNum}
        </div>
      </div>
      <div css={articleContentWrapperCSS}>
        <div css={articleTitleCSS}>{beverageName}</div>
        <div css={articleManuCSS}>{manufacturer}</div>

        <div css={articleUserCSS}>{userName}</div>
        <p>{content}</p>
      </div>
    </div>
  );
}

const articleFormWrapperCSS = css`
  display: flex;
`;

const articleRateWrapperCSS = css`
  flex: 1;
  display: flex;
  justify-content: center;
  margin-right: 10px;
`;

const articleContentWrapperCSS = css`
  flex: 3;
  margin-left: 10px;
  font-size: var(--font-size-h6);
  width: calc(66% - 20px);
`;

const articleRateCircleCSS = css`
  width: 62px;
  height: 62px;
  border-radius: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  /* background-color: #ff8a00; */
  color: var(--default-white-color);
  font-size: var(--font-size-h2);
  font-weight: var(--font-weight-bold);
`;

const articleTitleCSS = css`
  font-size: var(--font-size-h5);
  font-weight: var(--font-weight-semibold);
  margin-right: 10px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  margin-bottom: 12px;
`;

const articleManuCSS = css`
  color: var(--gray-color-2);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  margin-bottom: 12px;
`;

const articleUserCSS = css`
  margin-bottom: 12px;
`;
