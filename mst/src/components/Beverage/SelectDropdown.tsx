import { css } from "@emotion/react";
import { useEffect, useRef, useState } from "react";
import { ArrowDropDownIcon } from "@mui/x-date-pickers";

export interface OptionsProps {
  optionList: string[];
  onSelect: (option: string) => void;
}

export default function SelectDropDown({ optionList, onSelect }: OptionsProps) {
  // SELECT OPTION
  const [selectedOption, setSelectedOption] = useState("");

  // DROPDOWN OPEN/CLOSE
  const [isOpen, setIsOpen] = useState(false);
  const wrapperRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    // 초기값 update
    if (optionList.length > 0) {
      setSelectedOption(optionList[0]);
    }
  }, [optionList]);

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const handleOptionClick = (item: string) => {
    // 현재 Dropdown 값 update
    setSelectedOption(item);
    // 상위 formdata 값 update
    onSelect(item);
    // 선택시 DropDown 닫기
    setIsOpen(false);
  };

  useEffect(() => {
    function handleClickOutside(event: MouseEvent) {
      if (
        wrapperRef.current &&
        !wrapperRef.current.contains(event.target as Node)
      ) {
        setIsOpen(false);
      }
    }

    if (isOpen) {
      document.addEventListener("mousedown", handleClickOutside);
    } else {
      document.removeEventListener("mousedown", handleClickOutside);
    }

    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [isOpen]);

  return (
    <div css={selectWrapperCSS} onClick={toggleDropdown} ref={wrapperRef}>
      <div css={selectInputWrapperCSS}>
        {optionList.length !== 0 ? selectedOption : "사이즈가 없습니다"}
      </div>
      {isOpen ? (
        <ArrowDropDownIcon
          css={[inputIconCSS, { transform: "rotate(180deg)" }]}
        />
      ) : (
        <ArrowDropDownIcon css={inputIconCSS} />
      )}
      {isOpen && (
        <ul css={optionWrapperCSS}>
          {optionList.map((option, idx) => (
            <li
              css={optionContentCSS}
              key={idx}
              onClick={() => handleOptionClick(option)}
            >
              {option}
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
  height: 150px;
  overflow-y: auto;
  &::-webkit-scrollbar {
    display: none;
  }
`;

const optionContentCSS = css`
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
`;
