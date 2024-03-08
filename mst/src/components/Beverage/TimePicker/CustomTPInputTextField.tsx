import { InputAdornment, TextField, TextFieldProps } from "@mui/material";
import { ArrowDropDownIcon, CalendarIcon } from "@mui/x-date-pickers";

// DatePicker Modal을 띄우기 전 input
export function CustomTPTextField(props: TextFieldProps) {
  return (
    <TextField
      {...props}
      InputProps={{
        endAdornment: (
          <InputAdornment
            position="end"
            sx={{
              position: "fixed",
              right: "25px",
            }}
          >
            <ArrowDropDownIcon
              sx={{
                color: "var(--gray-color-4)",
                width: "45px",
                height: "50px",
              }}
            />
          </InputAdornment>
        ),
      }}
    />
  );
}
