import { css } from "@emotion/react";
import dayjs from "dayjs";

interface CmContentProps {
  title: string;
  author: string;
  timestamp: string;
  views: number;
  replies: number;
}

export default function CommunityForm({
  title,
  author,
  timestamp,
  views,
  replies,
}: CmContentProps) {
  const currentTime = dayjs();
  const createdTime = dayjs(timestamp);

  // 하루이상 차이나면 기존의 시간에서 날짜로 형식 변경
  const formattedTimestamp =
    currentTime.diff(createdTime, "day") >= 1
      ? createdTime.format("MM.DD")
      : createdTime.format("HH:mm");

  return (
    <>
      <div css={cmContentTitleCSS}>{title}</div>
      <div css={cmContentUserCSS}>{author}</div>
      <div css={cmContentInfoCSS}>
        <span>{formattedTimestamp} · </span>
        <span>조회수 {views} · </span>
        <span>댓글 {replies}</span>
      </div>
    </>
  );
}

const cmContentTitleCSS = css`
  font-size: var(--font-size-h5);
  font-weight: var(--font-weight-semibold);
  margin-bottom: 11px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
`;

const cmContentUserCSS = css`
  font-size: var(--font-size-h6);
  color: var(--gray-color-3);
  margin-bottom: 11px;
`;

const cmContentInfoCSS = css`
  font-size: var(--font-size-h6);
  color: var(--gray-color-3);
`;
