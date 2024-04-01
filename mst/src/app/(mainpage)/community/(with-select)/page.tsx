"use client";
import { css } from "@emotion/react";
import React from "react";
import Form from "@/components/common/Form/Form";
import PostForm from "@/components/Community/PostForm";
import PencilButton from "@/components/common/Button/PencilButton";
import { useRouter } from "next/navigation";

interface PostFormData {
  id: number;
  title: string;
  author: string;
  timestamp: string;
  views: number;
  replies: number;
}

const postDummyData: PostFormData[] = [
  {
    id: 1,
    title: "첫 번째입니다adsssssssssssssssssssssssssssssssssssssss.",
    author: "김닉네임",
    timestamp: "2024-03-18T12:54:00",
    views: 60,
    replies: 1,
  },
  {
    id: 2,
    title: "두 번째입니다.",
    author: "이닉네임",
    timestamp: "2024-03-13T13:00:00",
    views: 45,
    replies: 2,
  },
  {
    id: 3,
    title: "세 번째입니다.",
    author: "박닉네임",
    timestamp: "2024-03-20T13:10:00",
    views: 30,
    replies: 0,
  },
  {
    id: 4,
    title: "네 번째 댓글입니다.",
    author: "최닉네임",
    timestamp: "2024-03-20T13:20:00",
    views: 20,
    replies: 3,
  },
];

function Community() {
  const router = useRouter();

  return (
    <div>
      <div
        onClick={() => {
          console.log("Clicked");
        }}
        css={communityContentCSS}
      >
        사람들과 이야기를 나눠보세요.
      </div>
      <div>
        {postDummyData.map((item) => (
          <Form
            onClick={() => {
              router.push(`/${item.id}`);
            }}
            cssProps={postFormWrapperCSS}
            shadow={true}
            key={item.id}
            content={
              <PostForm
                onClick={() => {
                  console.log("Clicked");
                  console.log(item.id);
                  // router.push(`/${item.id}`);
                }}
                title={item.title}
                author={item.author}
                timestamp={item.timestamp}
                views={item.views}
                replies={item.replies}
              />
            }
          />
        ))}
      </div>
      <PencilButton />
      <div css={emptyNavCSS} />
    </div>
  );
}

const communityContentCSS = css`
  font-size: var(--font-size-h6);
  margin-bottom: 20px;
`;

const postFormWrapperCSS = css`
  margin-bottom: 13px;
`;

const emptyNavCSS = css`
  height: 80px;
`;

export default Community;
