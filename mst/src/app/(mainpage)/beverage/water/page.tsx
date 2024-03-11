"use client";
import { css } from "@emotion/react";
import Button from "@/components/common/Button/Button";
import CustomDatePicker from "@/components/Beverage/DatePicker/CustomDatePicker";
import CustomTimePicker from "@/components/Beverage/TimePicker/CustomTimePicker";
import SubmitFormWrapper from "@/components/Beverage/SubmitFormWrapper";
import { ChangeEvent, useState } from "react";
import { MUGCUP, PAPERCUP, PLASTICBOTTLE } from "@/assets/pictures";

const fixedWaterValue = [
  { name: "종이컵", value: 150, svgName: "PAPERCUP" },
  { name: "머그컵", value: 300, svgName: "MUGCUP" },
  { name: "페트병", value: 500, svgName: "PLASTICBOTTLE" },
];

function WaterCreate() {
  // WaterBtn 정리
  const selectPictureBtn = (svgName: string, color: string) => {
    switch (svgName) {
      case "PAPERCUP":
        return <PAPERCUP color={color} />;
      case "MUGCUP":
        return <MUGCUP color={color} />;
      case "PLASTICBOTTLE":
        return <PLASTICBOTTLE color={color} />;
      default:
        return null;
    }
  };

  const [waterAmount, setWaterAmount] = useState(0);

  // btn 색깔 관리
  const handleWaterBtnClick = (val: number) => {
    setWaterAmount(val);
  };

  // waterAmount관리
  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const newValue = parseFloat(e.target.value);
    setWaterAmount(newValue);
  };

  return (
    <div>
      <form>
        <div css={waterAmountWrapperCSS}>
          <div css={waterContentTitleCSS}>섭취량</div>
          {/* 상단버튼 */}
          <div css={fixedWaterBtnWrapperCSS}>
            {fixedWaterValue.map((item, idx) => (
              <div
                key={idx}
                css={[
                  fixedWaterBtnCSS,
                  item.value === waterAmount && {
                    color: "var(--default-water-color)",
                    border: "1px solid var(--default-water-color)",
                  },
                ]}
              >
                <div
                  css={btnPictureWrapperCSS}
                  onClick={() => handleWaterBtnClick(item.value)}
                >
                  <div
                    css={[
                      btnPictureCSS,
                      // mugcup 그림 상 중앙정렬을 맞추기 위해 필요
                      item.svgName === "MUGCUP" && {
                        marginLeft: "10px",
                      },
                    ]}
                  >
                    {selectPictureBtn(
                      item.svgName,
                      item.value === waterAmount
                        ? "var(--default-water-color)"
                        : "var(--gray-color-4)"
                    )}
                  </div>
                </div>
                <div
                  css={[
                    waterBtnContentWrapperCSS,
                    item.value === waterAmount && {
                      color: "var(--default-water-color)",
                    },
                  ]}
                >
                  <div css={waterBtnNameCSS}>{item.name}</div>
                  <div css={waterBtnAmountCSS}>{item.value}ml</div>
                </div>
              </div>
            ))}
          </div>
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
        <div>
          <div css={waterContentTitleCSS}>섭취시간</div>
          <CustomDatePicker />
          <CustomTimePicker />
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
  margin-bottom: 15px;
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

const fixedWaterBtnWrapperCSS = css`
  margin-bottom: 56px;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;
const fixedWaterBtnCSS = css`
  flex-grow: 1;
  margin: 0 10px;
  min-width: 90px;
  height: 180px;
  background-color: var(--default-white-color);
  border: 1px solid var(--gray-color-4);
  border-radius: 15px;
`;

const btnPictureWrapperCSS = css`
  height: 110px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  position: relative;
`;

const btnPictureCSS = css`
  position: absolute;
  bottom: 0px;
`;

const waterBtnContentWrapperCSS = css`
  height: 70px;
  text-align: center;
  padding: 20px 10px;
  color: var(--gray-color-4);
`;

const waterBtnNameCSS = css`
  font-size: var(--font-size-h6);
  margin-bottom: 5px;
`;

const waterBtnAmountCSS = css`
  font-size: var(--font-size-h5);
`;
