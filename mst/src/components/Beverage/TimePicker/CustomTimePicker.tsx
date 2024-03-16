import { useEffect, useRef, useState } from "react";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { css } from "@emotion/react";

import {
  ThemeProvider,
  createTheme,
} from "@mui/material";
import dayjs, { Dayjs } from "dayjs";
import CustomDigitalClock from "./CustomDigitalClock";
import { ArrowDropDownIcon } from "@mui/x-date-pickers";

const theme = createTheme({
  typography: {
    fontFamily: "SCoreDream",
  },
});

export default function CustomTimePicker() {
  // 5분단위로 무조건 바꾸는 게 필요한가?

  const [selectedTime, setSelectedTime] = useState<Dayjs | null>(dayjs());
  const [isOpen, setIsOpen] = useState(false);
  const wrapperRef = useRef<HTMLDivElement | null>(null);

  const id = isOpen ? "time-picker-popper" : undefined;

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };
  const handleTimeChange = (date: Dayjs | null) => {
    setSelectedTime(date);
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
    <ThemeProvider theme={theme}>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <div css={dropDownWrapperCSS} ref={wrapperRef}>
          <div css={selectInputWrapperCSS} onClick={toggleDropdown}>
            <div></div>
            {selectedTime
              ? selectedTime.format("A hh:mm")
              : "설정된 시간이 없습니다"}
          </div>
          {isOpen ? (
            <ArrowDropDownIcon
              css={[inputIconCSS, { transform: "rotate(180deg)" }]}
            />
          ) : (
            <ArrowDropDownIcon css={inputIconCSS} />
          )}
          {isOpen && (
            <div css={optionWrapperCSS} id={id}>
              <CustomDigitalClock
                selectedTime={selectedTime}
                onChange={handleTimeChange}
              />
            </div>
          )}
        </div>
      </LocalizationProvider>
    </ThemeProvider>
  );
}

const dropDownWrapperCSS = css`
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
  width: 100%;
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
