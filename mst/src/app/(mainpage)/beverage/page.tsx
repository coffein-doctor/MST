"use client";
import Form from "@/components/Form/Form";
import { css } from "@emotion/react";
import { LEFTARROW, SEARCH, PENCIL } from "@/assets/icons";

function Beverage() {
  return (
    <div>
      <div css={topBarWrapperCSS}>
        <div css={backIconWrapperCSS}>
          <div>{LEFTARROW}</div>
        </div>
        <div css={searchBarWrapperCSS}>
          <div css={searchIconWrapperCSS}>
            <div>{SEARCH}</div>
          </div>
          <input
            css={searchInputCSS}
            type="text"
            placeholder="검색어를 입력해주세요"
          />
        </div>
      </div>
      <div css={emptyTopBarCSS}></div>
      <div css={resultWrapperCSS}>
        <div css={favBevSubTitleCSS}>즐겨찾기</div>
        {/* <Form css={favBeverageWrapperCSS}>
          <div>dfsdsf</div>
        </Form> */}
      </div>
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
  font-size: var(--font-size-h5);
  font-weight: var(--font-weight-bold);
`;

const favBevWrapperCSS = css`
  margin-top: 13px;
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
