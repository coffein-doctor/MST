import { css } from "@emotion/react";
import Image from "next/image";
import BrownCircle from "../../../assets/png/BrownCircle.png";
import ReplySet from "./ReplySet";
import CommentEditModal from "./CommentEditModal";
import getFormattedTimestamp from "@/utils/getFormattedTimeStamp";

interface CommentSetProps {
	id:number;
  username: string;
  createdDate: string;
  content: string;
  postId: number;
  parentId: number | null;
	isCommentOpen: boolean;
  handleReplyInput:(idx:number) => void;
}

export default function CommentSet({
	id,
  username,
  createdDate,
  content,
  postId,
  parentId,
	isCommentOpen,
  handleReplyInput,
}: CommentSetProps) {
	
  const formattedDate = getFormattedTimestamp(createdDate);

	
  return (
    <div css={commentFormWrapperCSS}>
      <div css={profileImgWrapperCSS}>
        <Image src={BrownCircle} alt="프로필사진" css={profileImgCSS} />
      </div>
      <div css={commentContentWrapperCSS}>
        <div css={commentUpsideWrapperCSS}>
          <div css={commentUserCSS}>{username}</div>
          <CommentEditModal />
        </div>
        <div css={commentTimeCSS}>{formattedDate}</div>
        <div css={commentContentCSS}>{content}</div>
        <div css={replyBtnCSS} onClick={()=>handleReplyInput(id)}>
          대댓글 {"1"}개
        </div>
        {/* Reply */}
        {isCommentOpen && <ReplySet />}
      </div>
    </div>
  );
}

const commentFormWrapperCSS = css`
  display: flex;
  margin-bottom: 20px;
`;

const profileImgWrapperCSS = css`
  flex: 1;
  display: flex;
  justify-content: center;
`;

const commentContentWrapperCSS = css`
  flex: 5;
  padding: 0px 8px;
`;

const profileImgCSS = css`
  width: 45px;
  height: 45px;
`;

const commentUpsideWrapperCSS = css`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 5px;
`;

const commentUserCSS = css`
  font-size: var(--font-size-h5);
`;

const commentTimeCSS = css`
  font-size: var(--font-size-h6);
  color: var(--gray-color-3);
  margin-bottom: 10px;
`;

const commentContentCSS = css`
  font-size: var(--font-size-h5);
  margin-bottom: 10px;
`;

const replyBtnCSS = css`
  color: var(--gray-color-3);
  font-size: var(--font-size-h5);
  margin-bottom: 20px;
`;
