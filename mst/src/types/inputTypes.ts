import { SerializedStyles } from "@emotion/react";

export type InputPropsType = {
  id: string;
  name: string;
  type?: string;
  value?: string | number;
  placeholder?: string;
  cssProps?: SerializedStyles;
  onChange: any;
};
