"use client";
import { CHAT, FULLHEART } from "@/assets/svgs";
import CommentSet from "@/components/Community/Comment/CommentSet";
import BasicTopBar from "@/components/common/TopBar/BasicTopBar";
import { css } from "@emotion/react";
import BrownCircle from "../../../../assets/png/BrownCircle.png";
import Image from "next/image";
import { useEffect, useState } from "react";
import getFormattedTimestamp from "@/utils/getFormattedTimeStamp";
import BasicInput from "@/components/common/Form/BasicInput";

interface CommunityParams {
  params: { id: number };
}

interface Post {
  title: string;
  user: string;
  date: string;
  views: number;
  comments: number;
  content: string;
  likes: number;
}

interface Comment {
  id: number;
  username: string;
  createdDate: string;
  content: string;
  postId: number;
  parentId: number | null;
}

const dummyPost: Post = {
  title: "제목제목제목",
  user: "김작성자",
  date: "2024-01-02T00:00:00+00:00",
  views: 60,
  comments: 1,
  content:
    "어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구",
  likes: 3,
};

const dummyComments: Comment[] = [
  {
    id: 1,
    content: "댓글내용1",
    createdDate: "2024-04-01T00:00:00+00:00",
    username: "이닉네임1",
    postId: 1,
    parentId: null,
  },
  {
    id: 3,
    content: "댓글내용2",
    createdDate: "2024-01-03T00:00:00+00:00",
    username: "이닉네임2",
    postId: 1,
    parentId: null,
  },
  {
    id: 2,
    content: "1번대댓글대댓글",
    createdDate: "2024-01-03T00:00:00+00:00",
    username: "이닉네임2",
    postId: 1,
    parentId: 1,
  },
];

export default function PostDetail({ params: { id } }: CommunityParams) {
  const formattedDate = getFormattedTimestamp(dummyPost.date);

  // map형식으로 대댓글 열림 닫힘 상태 관리
  const [commentStates, setCommentStates] = useState<Map<number, boolean>>(
    new Map()
  );
  const handleReplyInput = (id: number) => {
    setCommentStates((prev) => {
      const newCommentStates = new Map(prev);
      newCommentStates.forEach((_, key) => {
        if (key === id) {
          const currentValue = newCommentStates.get(key);
          newCommentStates.set(key, !currentValue);
        } else {
          newCommentStates.set(key, false);
        }
      });
      return newCommentStates;
    });
  };

  useEffect(() => {
    const initialCommentStates = new Map<number, boolean>();
    dummyComments.forEach((item) => {
      if (item.parentId === null) {
        initialCommentStates.set(item.id, false);
      }
    });
    setCommentStates(initialCommentStates);
  }, []);

  return (
    <div>
      <BasicTopBar content="커뮤니티" />
      <div css={postInfoWrapperCSS}>
        <div css={postTitleCSS}>{dummyPost.title}</div>
        <div css={postUserCSS}>{dummyPost.user}</div>
        <div css={postInfoCSS}>
          <span>{formattedDate} · </span>
          <span>조회수 {dummyPost.views} · </span>
          <span>댓글 {dummyPost.comments}</span>
        </div>
      </div>
      <hr css={hrCSS} />
      {/* 글내용 */}
      <div css={postContentWrapperCSS}>
        <div css={editDeleteBtnWrapperCSS}>
          <div css={[editDeleteBtnCSS, { marginRight: "12px" }]}>수정</div>
          <div css={[editDeleteBtnCSS, { marginRight: "17px" }]}>삭제</div>
        </div>
        <p css={postContentCSS}>{dummyPost.content}</p>
        <div css={likedBtnWrapperCSS}>
          <div css={likedBtnCSS}>
            <div css={likedBtnIconCSS}>{FULLHEART}</div>
            <div css={likedBtnContentCSS}>{dummyPost.likes}</div>
          </div>
        </div>
      </div>
      <hr css={[hrCSS, { marginBottom: "20px" }]} />
      {/* 댓글 */}
      <div>
        <div css={commentTitleCSS}>댓글</div>
        <div>
          {dummyComments.map((item) => (
            <CommentSet
              key={item.id}
              id={item.id}
              username={item.username}
              createdDate={item.createdDate}
              content={item.content}
              postId={item.postId}
              parentId={item.parentId}
              handleReplyInput={() => handleReplyInput(item.id)}
              isCommentOpen={commentStates.get(item.id) || false}
            />
          ))}
        </div>
      </div>
      <div css={emptyCommentCSS} />
      {/* 댓글 입력창 */}
      <div css={commentInputWrapperCSS}>
        <Image src={BrownCircle} alt="프로필사진" css={profileImgCSS} />
        <BasicInput
          id="content"
          name="content"
          placeholder={
            ![...commentStates.values()].some((state) => state === true)
              ? "댓글을 입력해주세요"
              : "대댓글을 입력해주세요"
          }
          cssProps={inputPlaceholderStyle}
        />
        <button css={commentBtnWrapperCSS}>{CHAT}</button>
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
  margin-top: 15px;
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

const commentInputWrapperCSS = css`
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 64px;
  background-color: var(--default-white-color);
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 12px 20px;
`;

const profileImgCSS = css`
  width: 40px;
  height: 40px;
  margin-right: 10px;
`;

const amountInputContentCSS = css`
  flex: 1 0 auto;
  border: none;
  outline: none;
  background-color: transparent;
  font-size: var(--font-size-h5);
  &::placeholder {
    font-size: var(--font-size-h5);
    color: var(--gray-color-4);
  }
`;

const inputPlaceholderStyle = css`
  &::placeholder {
    color: var(--gray-color-4);
  }
`;

const commentBtnWrapperCSS = css`
  width: 40px;
  height: 40px;
  border-radius: 100%;
  background-color: var(--default-yellow-color);
  display: flex;
  padding-top: 3px;
  justify-content: center;
  align-items: center;
`;

const emptyCommentCSS = css`
  height: 64px;
`;
