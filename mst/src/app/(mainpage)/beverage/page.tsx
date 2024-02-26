"use client";
import Form from "@/components/common/Form/Form";
import { css } from "@emotion/react";
import { LEFTARROW, SEARCH, PENCIL } from "@/assets/icons";
import BeverageForm from "@/components/Beverage/BeverageForm";
import { useRouter } from "next/navigation";

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
    liked: true,
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

  return (
    <div>
      {/* 상단바 */}
      <div css={topBarWrapperCSS}>
        <div css={backIconWrapperCSS}>
          <div onClick={() => router.back()}>{LEFTARROW}</div>
        </div>
        <div css={searchBarWrapperCSS}>
          <div css={searchIconWrapperCSS}>
            <div>{SEARCH}</div>
          </div>
          <input
            css={searchInputCSS}
            type="text"
            placeholder="검색어를 입력해주세요"
            autoFocus
          />
        </div>
      </div>
      <div css={emptyTopBarCSS}></div>
      {/* 결과 */}
      <div css={resultWrapperCSS}>
        <div css={favBevSubTitleCSS}>즐겨찾기</div>
        {testFormData.length === 0 ? (
          <div css={emptyFavTextWrapperCSS}>
            <div css={emptyFavTextCSS}>즐겨찾기한 음료가 없습니다</div>
          </div>
        ) : (
          testFormData.map((item) => (
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
                />
              }
            />
          ))
        )}
      </div>
      {/* 작성버튼 */}
      <button css={registerBtnWrapperCSS}>
        <div>{PENCIL}</div>
      </button>
    </div>
  );
}

// SearchBar

const topBarWrapperCSS = css`
  width: 100vw;
  height: 64px;
  background-color: var(--gray-color-2);
  position: fixed;
  top: 0px;
  left: 0px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0px 20px;
`;

const backIconWrapperCSS = css`
  margin-right: 40px;
`;

const searchBarWrapperCSS = css`
  flex: 1 0 auto;
  height: 40px;
  background-color: #e7e7e7;
  border-radius: 30px;
  padding: 0px 15px;
  display: flex;
  align-items: center;
`;

const searchIconWrapperCSS = css`
  margin-right: 5px;
  height: 19px;
`;

const searchInputCSS = css`
  border: none;
  outline: none;
  background-color: transparent;
  flex: 1 0 auto;
  font-size: var(--font-size-h5);

  &::placeholder {
    font-size: var(--font-size-h5);
  }
`;

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
  margin-top: 30vh;
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

// Register Btn
const registerBtnWrapperCSS = css`
  height: 50px;
  width: 50px;
  border-radius: 100%;
  background-color: var(--default-yellow-color);
  position: fixed;
  bottom: 20px;
  right: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export default Beverage;
