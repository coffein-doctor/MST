import getFormattedTimestamp from "@/utils/getFormattedTimeStamp";
import { css } from "@emotion/react";

interface PostContentProps {
  title: string;
  author: string;
  timestamp: string;
  views: number;
  replies: number;
  onClick?(): any;
}

export default function PostForm({
  title,
  author,
  timestamp,
  views,
  replies,
}: PostContentProps) {
  const formattedTimestamp = getFormattedTimestamp(timestamp);

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
