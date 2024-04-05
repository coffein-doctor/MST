"use client";
import { css } from "@emotion/react";
import Button from "@/components/common/Button/Button";
import CustomDatePicker from "@/components/Beverage/DatePicker/CustomDatePicker";
import CustomTimePicker from "@/components/Beverage/TimePicker/CustomTimePicker";
import SubmitFormWrapper from "@/components/Beverage/SubmitFormWrapper";
import { ChangeEvent, useState } from "react";
import WaterAmountButtons from "@/components/Beverage/WaterAmountButtons";

function WaterCreate() {
  const [waterAmount, setWaterAmount] = useState(0);

  // btn 색깔 관리
  const handleWaterAmountBtnClick = (val: number) => {
    setWaterAmount(val);
  };

  // waterAmount관리
  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const newValue = parseInt(e.target.value);
    setWaterAmount(newValue);
  };

  return (
    <div>
      <form>
        <div css={waterAmountWrapperCSS}>
          <div css={waterContentTitleCSS}>섭취량</div>
          {/* 상단버튼 */}
          <WaterAmountButtons
            waterAmount={waterAmount}
            handleWaterAmountBtnClick={handleWaterAmountBtnClick}
          />
          {/* 직접입력 */}
          <SubmitFormWrapper
            leftLabel="섭취량"
            rightLabel="ml"
            id="water-amount"
          >
            <input
              id="water-amount"
              name="waterAmount"
              value={waterAmount}
              onChange={handleChange}
              type="number"
              css={waterAmountInputContentCSS}
            />
          </SubmitFormWrapper>
        </div>
        <div css={timeDatePickerWrapperCSS}>
          <div css={waterContentTitleCSS}>섭취시간</div>
          <div>
            <CustomDatePicker />
            <CustomTimePicker />
          </div>
        </div>
        <Button content="추가하기" />
      </form>
    </div>
  );
}
export default WaterCreate;

const waterAmountWrapperCSS = css`
  margin-bottom: 75px;
`;
const waterContentTitleCSS = css`
  font-size: var(--font-size-h3);
  font-weight: var(--font-weight-bold);
  text-align: center;
  margin-bottom: 20px;
`;

const waterAmountInputContentCSS = css`
  flex: 1 0 auto;
  border: none;
  outline: none;
  background-color: transparent;
  font-size: var(--font-size-h4);
  padding-bottom: 3px;
  text-align: end;
`;

const timeDatePickerWrapperCSS = css`
  margin-bottom: 103px;
`;