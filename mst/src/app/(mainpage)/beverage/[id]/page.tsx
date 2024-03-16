"use client";
import { css } from "@emotion/react";
import BasicHR from "@/components/Beverage/BasicHR";
import { EMPTYHEART } from "@/assets/icons";
import Image from "next/image";
import BrownCircle from "@/assets/png/BrownCircle.png";
import OrangeCircle from "@/assets/png/OrangeCircle.png";
import Button from "@/components/common/Button/Button";
import CustomTimePicker from "@/components/Beverage/TimePicker/CustomTimePicker";
import CustomDatePicker from "@/components/Beverage/DatePicker/CustomDatePicker";
import SubmitFormWrapper from "@/components/Beverage/SubmitFormWrapper";
import { ChangeEvent, useState } from "react";
import SelectDropDown from "@/components/Beverage/SelectDropdown";

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

function BeverageDetail({ params: { id } }: BeverageParams) {
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
  return (
    <div>
      {/* 상단 Detail */}

      <div css={detailWrapperCSS}>
        <div css={detailTitleWrapperCSS}>
          <div css={detailTitleCSS}>카페라떼</div>
          <div>{EMPTYHEART}</div>
        </div>
        <div css={detailManufacturerCSS}>스타벅스</div>
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
              <div css={detailTextAmountCSS}>300mg</div>
            </span>
          </div>
          <div css={imageWrapperCSS}>
            <Image src={OrangeCircle} alt="sugar" css={detailImgCSS} priority />
            <span css={detailImgTextWrapper}>
              <div css={detailTextTitleCSS}>당</div>
              <div css={detailTextAmountCSS}>10g</div>
            </span>
          </div>
        </div>
      </div>
      <BasicHR />
      {/* FORM */}
      <form>
        <div css={amountWrapperCSS}>
          <div css={contentTitleCSS}>섭취량</div>

          {/* DropDown */}
          <SelectDropDown options={testOptions} />
          <br css={emptyCSS} />
          <SubmitFormWrapper>
            <div css={plusMinusBtnCSS} onClick={handleDecrease}>
              -
            </div>
            <input
              id="amount"
              name="amount"
              value={amount}
              onChange={handleChange}
              type="number"
              css={amountInputContentCSS}
            />
            <div css={plusMinusBtnCSS} onClick={handleIncrease}>
              +
            </div>
          </SubmitFormWrapper>
        </div>
        <div css={amountWrapperCSS}>
          <div css={contentTitleCSS}>섭취시간</div>
          <CustomDatePicker />
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

const amountInputContentCSS = css`
  flex: 1 0 auto;
  border: none;
  outline: none;
  background-color: transparent;
  font-size: var(--font-size-h4);
  padding-bottom: 3px;
  text-align: center;
`;

const plusMinusBtnCSS = css`
  color: var(--gray-color-4);
  font-size: var(--font-size-h3);
  font-weight: var(--font-weight-bold);
`;

const emptyCSS = css`
  width: 12px;
`;

export default BeverageDetail;
