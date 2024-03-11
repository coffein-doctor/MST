import { useState } from "react";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";

import {
  InputAdornment,
  Popover,
  TextField,
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

  const handleTimeChange = (date: Dayjs | null) => {
    setSelectedTime(date);
  };

  // popover 관련(Ref보다 State가 더 적합)
  const [anchorEl, setAnchorEl] = useState<HTMLElement | null>(null);

  const handleOpen = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const open = Boolean(anchorEl);
  const id = open ? "time-picker-popper" : undefined;

  return (
    <ThemeProvider theme={theme}>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        {/* 입력창 */}
        <TextField
          id="time-picker-textField"
          value={selectedTime ? selectedTime.format("A hh:mm") : ""}
          InputProps={{
            readOnly: true,
            endAdornment: (
              <InputAdornment position="end" sx={{ position: "relative" }}>
                <ArrowDropDownIcon
                  sx={{
                    position: "absolute",
                    right: "2%",
                    color: "var(--gray-color-4)",
                  }}
                />
              </InputAdornment>
            ),
          }}
          onClick={handleOpen}
          sx={timePickerCSS}
        />

        {/* 팝업창 */}
        <Popover
          id={id}
          open={open}
          anchorEl={anchorEl}
          onClose={handleClose}
          anchorOrigin={{
            vertical: "bottom",
            horizontal: "center",
          }}
          transformOrigin={{
            vertical: "top",
            horizontal: "center",
          }}
          //popover 가로길이 설정
          slotProps={{
            paper: {
              sx: {
                width: "80%",
                borderRadius: "15px",
              },
            },
          }}
        >
          {/* 디지털 시계 */}
          <CustomDigitalClock
            selectedTime={selectedTime}
            onChange={handleTimeChange}
          />
        </Popover>
      </LocalizationProvider>
    </ThemeProvider>
  );
}

const timePickerCSS = {
  width: "100%",
  marginBottom: "12px",
  "& .MuiOutlinedInput-root": {
    color: "var(--default-black-color)",
    borderRadius: "15px",
    backgroundColor: "var(--default-white-color)",
    height: "40px",
  },
  "& .MuiOutlinedInput-input": {
    color: "var(--default-black-color)",
    fontSize: "var(--font-size-h4)",
    textAlign: "center",
  },
};
