import { THREEDOT } from "@/assets/icons";
import { css } from "@emotion/react";
import { useState } from "react";

interface CommentEditModalProps {
  toggleOpenModal: boolean;
  isModalOpen: boolean;
}

export default function CommentEditModal() {

  const [isModalOpen, setIsModalOpen] = useState(false);


  const toggleOpenModal = () => {
    setIsModalOpen(!isModalOpen);
  };

	const toChangeInput =()=> {
		
	}

  return (
    <div css={editDeleteModalBtnCSS}>
      <div onClick={toggleOpenModal}>
        {/* THREEDOT 아이콘 클릭 시 수정삭제 옵션을 보여줌 */}
        {THREEDOT}
      </div>
      {/* 수정삭제 옵션 */}
      {isModalOpen && (
        <div css={editDeleteModalWrapperCSS}>
          <div onClick={toChangeInput} css={editDeleteBtnCSS}>수정</div>
          <div css={editDeleteBtnCSS}>삭제</div>
        </div>
      )}
    </div>
  );
}

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
