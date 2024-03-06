import { InputAdornment, TextField, TextFieldProps } from "@mui/material";
import { CalendarIcon } from "@mui/x-date-pickers";

// DatePicker Modal을 띄우기 전 input
export function CustomTextField(props: TextFieldProps) {
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
              color: "var(--gray-color-4)",
              width: "30px",
              height: "60px",
            }}
          >
            <CalendarIcon />
          </InputAdornment>
        ),
      }}
    />
  );
}
