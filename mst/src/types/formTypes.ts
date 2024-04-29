import { SerializedStyles } from "@emotion/react";

export type FormPropsType = {
  content: any;
  cssProps?: SerializedStyles;
  shadow?: boolean;
  onClick?(): any;
};

export type SubmitFormPropsType = {
  id: string;
  position?: "top" | "middle" | "bottom";
  leftLabel?: string;
  rightLabel?: string;
  value?: string | number;
  onChange?: any;
  type?: "string" | "number";
  error?: string;
};
