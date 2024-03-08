import { InputAdornment, TextField, TextFieldProps } from "@mui/material";
import { CalendarIcon } from "@mui/x-date-pickers";

// DatePicker Modal을 띄우기 전 input
export function CustomDPTextField(props: TextFieldProps) {
  return (
    <TextField
      {...props}
      InputProps={{
        readOnly: true,
        endAdornment: (
          <InputAdornment
            position="end"
            sx={{
              position: "fixed",
              right: "35px",
            }}
          >
            <CalendarIcon
              sx={{
                color: "var(--gray-color-4)",
                width: "2px",
                height: "25px",
              }}
            />
          </InputAdornment>
        ),
      }}
    />
  );
}
