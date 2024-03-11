import { css } from "@emotion/react";
import { useRef, useState } from "react";
import { ArrowDropDownIcon } from "@mui/x-date-pickers";

export interface SelectOptionsProps {
  options: Option[];
}

export interface Option {
  value: string;
}

export default function SelectDropDown({ options }: SelectOptionsProps) {
  const [selectedOption, setSelectedOption] = useState<Option>(options[0]);
  const [isOpen, setIsOpen] = useState(false);

  const wrapperRef = useRef<HTMLUListElement>(null);

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const handleOptionClick = (item: Option) => {
    setSelectedOption(item);
    setIsOpen(false);
  };

  const handleTouchStart = (event: React.TouchEvent<HTMLUListElement>) => {
    const touchStartY = event.touches[0].clientY;

    if (wrapperRef.current) {
      wrapperRef.current.dataset.touchStartY = touchStartY.toString();
    }
  };

  const handleTouchMove = (event: React.TouchEvent<HTMLUListElement>) => {
    if (!wrapperRef.current || !wrapperRef.current.dataset.touchStartY) return;
    const touchMoveY = event.touches[0].clientY;
    const touchStartY = parseFloat(wrapperRef.current.dataset.touchStartY);
    const deltaY = touchStartY - touchMoveY;
    if (wrapperRef.current) wrapperRef.current.scrollTop += deltaY;
  };

  const handleTouchEnd = () => {
    if (wrapperRef.current) {
      wrapperRef.current.dataset.touchStartY = "";
    }
  };

  return (
    <div css={selectWrapperCSS} onClick={toggleDropdown}>
      <div css={selectInputWrapperCSS}>
        {selectedOption ? selectedOption.value : "사이즈가 없습니다"}
      </div>
      <ArrowDropDownIcon css={inputIconCSS} />
      {isOpen && (
        <ul
          css={optionWrapperCSS}
          onTouchStart={handleTouchStart}
          onTouchMove={handleTouchMove}
          onTouchEnd={handleTouchEnd}
          ref={wrapperRef}
        >
          {options.map((option, idx) => (
            <li
              css={optionContentCSS}
              key={idx}
              onClick={() => handleOptionClick(option)}
            >
              {option.value}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

const selectWrapperCSS = css`
  position: relative;
  height: 45px;
  border: solid 1px var(--gray-color-4);
  background-color: white;
  padding: 0px 20px;
  border-radius: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const selectInputWrapperCSS = css`
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
`;

const inputIconCSS = css`
  position: absolute;
  right: 15px;
  color: var(--gray-color-4);
`;

const optionWrapperCSS = css`
  position: absolute;
  width: 100%;
  top: calc(100% + 10px);
  z-index: 999;
  text-align: center;
  background-color: white;
  border: 1px solid var(--gray-color-4);
  border-radius: 15px;
  height: 130px;
  overflow-y: hidden;
  padding: 0;
  margin: 0;
  list-style-type: none;
`;

const optionContentCSS = css`
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
`;
