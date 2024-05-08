import { MUGCUP, PAPERCUP, PLASTICBOTTLE } from "@/assets/svgs";
import { css } from "@emotion/react";

const fixedWaterValue = [
  { name: "종이컵", value: 150, svgName: "PAPERCUP" },
  { name: "머그컵", value: 300, svgName: "MUGCUP" },
  { name: "페트병", value: 500, svgName: "PLASTICBOTTLE" },
];

interface WaterAmountButtonsProps {
  waterAmount: number;
  onSelect: (val: number) => void;
}

export default function WaterAmountButtons({
  waterAmount,
  onSelect,
}: WaterAmountButtonsProps) {
  const selectedColor = "var(--gray-color-1)";
  return (
    <div css={fixedWaterBtnWrapperCSS}>
      {fixedWaterValue.map((item, idx) => (
        <div
          key={idx}
          css={[
            fixedWaterBtnCSS,
            item.value === waterAmount && {
              color: "var(--gray-color-1)",
              border: "1px solid var(--gray-color-1)",
            },
          ]}
        >
          <div css={btnPictureWrapperCSS} onClick={() => onSelect(item.value)}>
            <div
              css={[
                btnPictureCSS,
                // mugcup 그림 상 중앙정렬을 맞추기 위해 필요
                item.svgName === "MUGCUP" && {
                  marginLeft: "10px",
                },
              ]}
            >
              {item.svgName === "PAPERCUP" && (
                <PAPERCUP
                  color={
                    item.value === waterAmount
                      ? selectedColor
                      : "var(--gray-color-4)"
                  }
                />
              )}
              {item.svgName === "MUGCUP" && (
                <MUGCUP
                  color={
                    item.value === waterAmount
                      ? selectedColor
                      : "var(--gray-color-4)"
                  }
                />
              )}
              {item.svgName === "PLASTICBOTTLE" && (
                <PLASTICBOTTLE
                  color={
                    item.value === waterAmount
                      ? selectedColor
                      : "var(--gray-color-4)"
                  }
                />
              )}
            </div>
          </div>
          <div
            css={[
              waterBtnContentWrapperCSS,
              item.value === waterAmount && {
                color: selectedColor,
              },
            ]}
          >
            <div css={waterBtnNameCSS}>{item.name}</div>
            <div css={waterBtnAmountCSS}>{item.value}ml</div>
          </div>
        </div>
      ))}
    </div>
  );
}

const fixedWaterBtnWrapperCSS = css`
  margin-bottom: 56px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 10px;
`;
const fixedWaterBtnCSS = css`
  flex-grow: 1;
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
