"use client";
import { FULLHEART } from "@/assets/icons";
import CommentSet from "@/components/Community/Comment/CommentSet";
import { css } from "@emotion/react";
interface CommunityParams {
  params: { id: number };
}
export default function PostDetail({ params: { id } }: CommunityParams) {
  return (
    <div>
      <div>상단바</div>
      {/* 글제목 및 정보 */}
      <div css={postInfoWrapperCSS}>
        <div css={postTitleCSS}>제목이 어쩌구저꺼지ㅑㅜㄹ나ㅣ우ㅑㅠㅈㄷㄹ</div>
        <div css={postUserCSS}>김작성자</div>
        <div css={postInfoCSS}>
          <span>{"24.01.02"} · </span>
          <span>조회수 {"60"} · </span>
          <span>댓글 {"1"}</span>
        </div>
      </div>
      <hr css={hrCSS} />
      {/* 글내용 */}
      <div css={postContentWrapperCSS}>
        <div css={editDeleteBtnWrapperCSS}>
          <div css={[editDeleteBtnCSS, { marginRight: "12px" }]}>수정</div>
          <div css={[editDeleteBtnCSS, { marginRight: "17px" }]}>삭제</div>
        </div>
        <p css={postContentCSS}>
          어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구
        </p>
        <div css={likedBtnWrapperCSS}>
          <div css={likedBtnCSS}>
            <div css={likedBtnIconCSS}>{FULLHEART}</div>
            <div css={likedBtnContentCSS}>3</div>
          </div>
        </div>
      </div>
      <hr css={[hrCSS, { marginBottom: "20px" }]} />
      {/* 댓글 */}
      <div>
        <div css={commentTitleCSS}>댓글</div>
        <div>
          <CommentSet />
          <div>대댓글</div>
        </div>
      </div>
    </div>
  );
}

const postInfoWrapperCSS = css`
  margin-bottom: 10px;
`;

const postTitleCSS = css`
  font-size: var(--font-size-h3);
  font-weight: var(--font-weight-bold);
  margin-bottom: 30px;
`;

const postUserCSS = css`
  color: var(--gray-color-1);
  font-size: var(--font-size-h5);
  margin-bottom: 13px;
`;
const postInfoCSS = css`
  text-align: end;
  color: var(--gray-color-3);
  font-size: var(--font-size-h6);
`;

const hrCSS = css`
  color: var(--gray-color-4);
  margin-bottom: 10px;
`;

const postContentWrapperCSS = css`
  margin-bottom: 20px;
`;

const editDeleteBtnWrapperCSS = css`
  display: flex;
  justify-content: end;
  align-items: center;
  margin-bottom: 30px;
`;

const editDeleteBtnCSS = css`
  color: var(--gray-color-3);
  font-size: var(--font-size-h5);
`;

const postContentCSS = css`
  font-size: var(--font-size-h4);
  margin-bottom: 25px;
`;

const likedBtnWrapperCSS = css`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const likedBtnCSS = css`
  border: 1px solid var(--gray-color-4);
  border-radius: 15px;
  padding: 12px 15px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const likedBtnIconCSS = css`
  margin-right: 7px;
`;

const likedBtnContentCSS = css`
  color: var(--gray-color-2);
  font-size: var(--font-size-h5);
`;

const commentTitleCSS = css`
  font-weight: var(--font-weight-bold);
  font-size: var(--font-size-h5);
  margin-bottom: 13px;
`;
