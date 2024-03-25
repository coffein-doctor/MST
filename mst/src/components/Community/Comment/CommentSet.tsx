import { css } from "@emotion/react";
import { useState } from "react";
import Image from "next/image";
import BrownCircle from "../../../assets/png/BrownCircle.png";
import { THREEDOT } from "@/assets/icons";
import ReplySet from "./ReplySet";

export default function CommentSet() {
  const [isReplyOpen, setIsReplyOpen] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const toggleOpenReply = () => {
    setIsReplyOpen(!isReplyOpen);
  };

  const toggleOpenModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  return (
    <div css={commentFormWrapperCSS}>
      <div css={profileImgWrapperCSS}>
        <Image src={BrownCircle} alt="프로필사진" css={profileImgCSS} />
      </div>
      <div css={commentContentWrapperCSS}>
        <div css={commentUpsideWrapperCSS}>
          <div css={commentUserCSS}>이닉네임</div>
          <div css={editDeleteModalBtnCSS}>
            <div onClick={toggleOpenModal}>
              {/* THREEDOT 아이콘 클릭 시 수정삭제 옵션을 보여줌 */}
              {THREEDOT}
            </div>
            {/* 수정삭제 옵션 */}
            {isModalOpen && (
              <div css={editDeleteModalWrapperCSS}>
                <div css={editDeleteBtnCSS}>수정</div>
                <div css={editDeleteBtnCSS}>삭제</div>
              </div>
            )}
          </div>
        </div>
        <div css={commentTimeCSS}>12:54</div>
        <div css={commentContentCSS}>
          댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용
        </div>
        <div css={replyBtnCSS} onClick={toggleOpenReply}>
          대댓글 1개
        </div>
        {/* Reply */}
        {isReplyOpen && <ReplySet />}
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

const editDeleteModalBtnCSS = css`
  position: relative;
`;

const editDeleteModalWrapperCSS = css`
  position: absolute;
  top: 100%;
  right: 0;
  width: 70px;
  border-radius: 15px;
  background-color: var(--default-white-color);
  font-size: var(--font-size-h5);
  color: var(--gray-color-3);
`;

const editDeleteBtnCSS = css`
  padding: 10px 18px;
  height: 34px;
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
  margin-bottom: 20px;
`;
