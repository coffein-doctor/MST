export type TopBarPropsType = {
  type: "basic" | "select" | "search";
  content: string;
};

export type BasicTopBarProps = {
  content: string;
};

export type SelectTopBarProps = {
  firstCategory: string;
  secondCategory: string;
  // onSelectCategory: (category: "first" | "second") => void;
};

export type SearchTopBarProps = {};
