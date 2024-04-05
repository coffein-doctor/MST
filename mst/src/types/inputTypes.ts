import { SerializedStyles } from "@emotion/react";

export type BasicInputPropsType = {
  id: string;
  name: string;
  type?: string;
  value?: string | number;
  placeholder?: string;
  cssProps?: SerializedStyles;
  onChange?: any;
};
