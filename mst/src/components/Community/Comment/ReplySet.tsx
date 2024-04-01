import { css } from "@emotion/react";
import Image from "next/image";
import BrownCircle from "../../../assets/png/BrownCircle.png";
import CommentEditModal from "./CommentEditModal";

export default function ReplySet() {
  return (
    <div css={replyFormWrapperCSS}>
      <div css={profileImgWrapperCSS}>
        <Image src={BrownCircle} alt="프로필사진" css={profileImgCSS} />
      </div>
      <div css={replyContentWrapperCSS}>
        <div css={replyUpsideWrapperCSS}>
          <div css={replyUserCSS}>저닉네임</div>
					<CommentEditModal />
        </div>
        <div css={replyTimeCSS}>13:53</div>
        <div css={replyContentCSS}>
          댓글내용댓글내용댓글내용댓글내22용댓글내용댓글내용댓글내용
          댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용
        </div>
      </div>
    </div>
  );
}

const replyFormWrapperCSS = css`
  display: flex;
`;

const profileImgWrapperCSS = css`
  flex: 1;
  display: flex;
  justify-content: center;
`;

const replyContentWrapperCSS = css`
  flex: 5;
  padding-left: 8px;
`;

const profileImgCSS = css`
  width: 45px;
  height: 45px;
`;

const replyUpsideWrapperCSS = css`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 5px;
`;

const replyUserCSS = css`
  font-size: var(--font-size-h5);
`;

const replyTimeCSS = css`
  font-size: var(--font-size-h6);
  color: var(--gray-color-3);
  margin-bottom: 10px;
`;

const replyContentCSS = css`
  font-size: var(--font-size-h5);
  margin-bottom: 10px;
`;

const replyBtnCSS = css`
  color: var(--gray-color-3);
  margin-bottom: 20px;
`;
