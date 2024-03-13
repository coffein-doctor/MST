"use client";
import Form from "@/components/common/Form/Form";
import { css } from "@emotion/react";
import BeverageForm from "@/components/Beverage/BeverageForm";
import { useRouter } from "next/navigation";
import { useState } from "react";
import PencilButton from "@/components/common/Button/PencilButton";
import SearchTopBar from "@/components/common/TopBar/SearchTopBar";

interface FormData {
  id: number;
  registNum: number;
  name: string;
  company: string;
  liked: boolean;
}
const testFormData: FormData[] = [
  {
    id: 1,
    name: "아메리카노",
    company: "스타벅스",
    registNum: 1000,
    liked: false,
  },
  {
    id: 2,
    name: "카페라떼",
    company: "스타벅스",
    registNum: 10,
    liked: true,
  },
  {
    id: 3,
    name: "자몽허니블랙티",
    company: "스타벅스",
    registNum: 105,
    liked: true,
  },
];
function Beverage() {
  const router = useRouter();

  const [data, setData] = useState(testFormData);

  // 여기서 liked: true 인것만 주는지 BE와 소통필요
  // const [data, setData] = useState(testFormData.filter((item) => item.liked));

  const toggleLiked = (id: number) => {
    const updatedData = data.map((item) => {
      if (item.id === id) {
        return { ...item, liked: !item.liked };
      }
      return item;
    });

    const filteredData = updatedData.filter((item) => item.liked === true);

    setData(filteredData);
  };

  // 좋아요 배열 반환
  return (
    <div>
      {/* 상단바 */}
			<SearchTopBar/>
      <div css={emptyTopBarCSS}></div>
      {/* 결과 */}
      <div css={resultWrapperCSS}>
        <div css={favBevSubTitleCSS}>즐겨찾기</div>
        {data.length === 0 ? (
          <div css={emptyFavTextWrapperCSS}>
            <div css={emptyFavTextCSS}>즐겨찾기한 음료가 없습니다</div>
          </div>
        ) : (
          data.map((item) => (
            <Form
              cssProps={favBevWrapperCSS}
              shadow={true}
              key={item?.id}
              content={
                <BeverageForm
                  registNum={item.registNum}
                  name={item.name}
                  company={item.company}
                  liked={item.liked}
                  toggleLiked={() => toggleLiked(item.id)}
                />
              }
            />
          ))
        )}
      </div>
      {/* 작성버튼 */}
			<PencilButton/>
    </div>
  );
}


// emptyTopBar
const emptyTopBarCSS = css`
  height: 64px;
`;

// FavBeverage

const resultWrapperCSS = css`
  padding: 20px 0px;
`;

const favBevSubTitleCSS = css`
  margin-top: 15px;
  margin-left: 20px;
  margin-bottom: 13px;
  font-size: var(--font-size-h5);
  font-weight: var(--font-weight-bold);
`;

const emptyFavTextWrapperCSS = css`
  margin-top: 35vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const emptyFavTextCSS = css`
  color: var(--gray-color-3);
  font-size: var(--font-size-h6);
`;

const favBevWrapperCSS = css`
  margin-bottom: 13px;
`;

export default Beverage;
