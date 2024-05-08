"use client";
import { css } from "@emotion/react";
import { EMPTYHEART } from "@/assets/svgs";
import Image from "next/image";
import BrownCircle from "@/assets/png/BrownCircle.png";
import OrangeCircle from "@/assets/png/OrangeCircle.png";
import Button from "@/components/common/Button/Button";
import CustomTimePicker from "@/components/Beverage/TimePicker/CustomTimePicker";
import CustomDatePicker from "@/components/Beverage/DatePicker/CustomDatePicker";
import { ChangeEvent, useEffect, useState } from "react";
import SelectDropDown from "@/components/Beverage/SelectDropdown";
import BasicInput from "@/components/common/Form/BasicInput";
import BasicTopBar from "@/components/common/TopBar/BasicTopBar";
import dayjs from "dayjs";

const testOptions = [
  { value: "톨사이즈" },
  { value: "그란데사이즈" },
  { value: "라지사이즈" },
  { value: "톨사이즈" },
  { value: "그란데사이즈그란데사이즈그란데사이즈" },
  { value: "그란데사이즈" },
  { value: "라지사이즈" },
  { value: "톨사이즈" },
  { value: "그란데사이즈" },
  { value: "라지사이즈" },
  { value: "톨사이즈" },
];
interface BeverageParams {
  params: { id: string };
}

export type BeverageByIdFormData = {
  name: string;
  company: string;
  caffeine: number | "";
  sugar: number | "";
  volume: number | "";
  size: string;
  // created_time: dayjs;
};

const initialBeverageByIdFormData: BeverageByIdFormData = {
  name: "",
  company: "",
  caffeine: "",
  sugar: "",
  volume: "",
  size: "",
};

function BeverageDetail({ params: { id } }: BeverageParams) {
  const [beverageByIdFormData, setBeverageByIdFormData] = useState(
    initialBeverageByIdFormData
  );

  // SIZE
  const [sizeOption, setSizeOption] = useState();

  // AMOUNT
  const [amount, setAmount] = useState(1);

  const handleDecrease = () => {
    setAmount((prev) => (prev - 0.5 >= 0 ? prev - 0.5 : 0));
  };

  const handleIncrease = () => {
    setAmount((prev) => (prev + 0.5 < 10 ? prev + 0.5 : 10));
  };

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const newValue = parseFloat(e.target.value);
    setAmount(newValue);
  };

  // SUBMIT

  // 기본정보 get
  useEffect(() => {
    // 내가 해결해야할거 : SSR조기 렌더링
		// https://www.youtube.com/watch?v=GgvE5fkIs9o
		// https://soobing.github.io/react/server-rendering-and-react-query/
		// https://xionwcfm.tistory.com/339#google_vignette
    // size어떻게 할거임?
    // created_date, time이 없음
    const tempRenderingData = {
      name: "카페라떼",
      company: "스타벅스",
      caffeine: 100,
      sugar: 20,
      volume: 250,
      size: "",
      // size: ["톨사이즈", "그란데사이즈", "라지사이즈"],
    };
    setBeverageByIdFormData(tempRenderingData);
  }, []);
  return (
    <div>
      <BasicTopBar content="음료 등록" />
      {/* 상단 Detail */}
      <div css={detailWrapperCSS}>
        <div css={detailTitleWrapperCSS}>
          <div css={detailTitleCSS}>{beverageByIdFormData.name}</div>
          <div>{EMPTYHEART}</div>
        </div>
        <div css={detailManufacturerCSS}>{beverageByIdFormData.company}</div>
        <div css={detailImgWrapperCSS}>
          <div css={imageWrapperCSS}>
            <Image
              src={BrownCircle}
              alt="caffeine"
              css={detailImgCSS}
              priority
            />
            <span css={detailImgTextWrapper}>
              <div css={detailTextTitleCSS}>카페인</div>
              <div css={detailTextAmountCSS}>
                {beverageByIdFormData.caffeine}mg
              </div>
            </span>
          </div>
          <div css={imageWrapperCSS}>
            <Image src={OrangeCircle} alt="sugar" css={detailImgCSS} priority />
            <span css={detailImgTextWrapper}>
              <div css={detailTextTitleCSS}>당</div>
              <div css={detailTextAmountCSS}>{beverageByIdFormData.sugar}g</div>
            </span>
          </div>
        </div>
      </div>
      <hr css={hrCSS} />
      {/* FORM */}
      <form>
        <div css={amountWrapperCSS}>
          <div css={contentTitleCSS}>섭취량</div>
          {/* DropDown */}
          <SelectDropDown options={testOptions} />
          <br css={emptyCSS} />
          <div css={inputWrapperCSS}>
            <div css={plusMinusBtnCSS} onClick={handleDecrease}>
              -
            </div>
            <BasicInput
              id="amount"
              name="amount"
              value={amount}
              onChange={handleChange}
              type="number"
              cssProps={css({
                textAlign: "center",
                fontSize: "var(--font-size-h4)",
              })}
            />
            <div css={plusMinusBtnCSS} onClick={handleIncrease}>
              +
            </div>
          </div>
        </div>
        <div css={amountWrapperCSS}>
          <div css={contentTitleCSS}>섭취시간</div>
          {/* <CustomDatePicker /> */}
          <CustomTimePicker />
        </div>
        <Button content="추가하기" />
      </form>
    </div>
  );
}
const detailWrapperCSS = css`
  margin-top: 30px;
`;

const detailTitleWrapperCSS = css`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
`;

const detailTitleCSS = css`
  font-size: var(--font-size-h2);
  font-weight: var(--font-weight-bold);
`;

const detailManufacturerCSS = css`
  color: var(--gray-color-2);
  font-size: var(--font-size-h3);
  font-weight: var(--font-weight-bold);
  margin-bottom: 33px;
`;

//Image
const detailImgWrapperCSS = css`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 36px;
`;

const imageWrapperCSS = css`
  position: relative;
`;

const detailImgCSS = css`
  width: 145px;
  height: auto;
`;
const detailImgTextWrapper = css`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
  font-size: 16px;
  z-index: 50;

  text-align: center;
`;

const detailTextTitleCSS = css`
  font-size: var(--font-size-h5);
  font-weight: var(--font-weight-bold);
  margin-bottom: 10px;
`;

const detailTextAmountCSS = css`
  font-size: var(--font-size-h3);
  font-weight: var(--font-weight-bold);
`;

// form
const amountWrapperCSS = css`
  margin-bottom: 103px;
`;
const contentTitleCSS = css`
  font-size: var(--font-size-h3);
  font-weight: var(--font-weight-bold);
  text-align: center;
  margin-bottom: 15px;
`;

const plusMinusBtnCSS = css`
  color: var(--gray-color-4);
  font-size: var(--font-size-h3);
  font-weight: var(--font-weight-bold);
`;

const emptyCSS = css`
  width: 12px;
`;

const inputWrapperCSS = css`
  height: 45px;
  border: solid 1px var(--gray-color-4);
  background-color: white;
  padding: 0px 20px;
  border-radius: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const hrCSS = css`
  color: var(--gray-color-4);
  margin-bottom: 30px;
`;

export default BeverageDetail;
