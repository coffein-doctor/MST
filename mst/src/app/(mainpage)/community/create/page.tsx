"use client";
import { css } from "@emotion/react";
import Button from "@/components/common/Button/Button";
import { ChangeEvent, useState } from "react";
import BasicTopBar from "@/components/common/TopBar/BasicTopBar";
import BasicInput from "@/components/common/Form/BasicInput";

export default function PostCreate() {
  const [postFormData, setPostFormData] = useState({
    username: "",
    title: "",
    content: "",
  });

  const handleInputChange = (
    event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = event.target;
    setPostFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  return (
    <div>
      <BasicTopBar content="글 작성하기" />
      <form method="submit">
        <BasicInput
          id="title"
          name="title"
          value={postFormData.title}
          onChange={handleInputChange}
          placeholder="제목을 입력해주세요"
          cssProps={titleInputCSS}
        />
        <hr css={hrCSS} />
        <textarea
          id="content"
          name="content"
          value={postFormData.content}
          css={contentTextAreaCSS}
          onChange={handleInputChange}
          placeholder="내용을 입력해주세요"
        />
        <Button content="작성하기" />
      </form>
    </div>
  );
}

const titleInputCSS = css`
  width: 100%;
  font-size: var(--font-size-h3);
  margin-bottom: 10px;

  &::placeholder {
    font-size: var(--font-size-h3);
    color: var(--gray-color-4);
  }
`;

const hrCSS = css`
  color: var(--gray-color-4);
  margin-bottom: 20px;
`;

const contentTextAreaCSS = css`
  width: 100%;
  height: 72vh;
  border: none;
  outline: none;
  background-color: transparent;
  font-size: var(--font-size-h4);
  padding-bottom: 3px;
  resize: none;

  &::placeholder {
    font-size: var(--font-size-h4);
    color: var(--gray-color-4);
  }
`;
