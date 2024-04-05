import { BasicInputPropsType } from "@/types/inputTypes";
import { css } from "@emotion/react";

export default function Input({
  id,
  name,
  cssProps,
  type,
  value,
  onChange,
  placeholder,
}: BasicInputPropsType) {
  return (
    <input
      css={[signUpInputCSS, cssProps]}
      id={id}
      name={name}
      type={type}
      value={value}
      onChange={onChange}
      placeholder={placeholder}
    />
  );
}

const signUpInputCSS = css`
  border: none;
  outline: none;
  background-color: transparent;
  flex: 1 0 auto;
  font-size: var(--font-size-h5);

  &::placeholder {
    font-size: var(--font-size-h5);
  }

  input[type="number"]::-webkit-inner-spin-button,
  input[type="number"]::-webkit-outer-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }
`;
