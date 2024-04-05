import { SerializedStyles } from "@emotion/react";

export type InputPropsType = {
  id: string;
  name: string;
  type?: string;
  value?: string | number;
  cssProps?: SerializedStyles;
  onChange: any;
};