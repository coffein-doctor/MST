"use client";
import SelectTopBar from "@/components/common/TopBar/SelectTopBar";
export default function CommunitySelectBarLayout({
  children,
}: {
  children: React.ReactNode;
}) {

  return (
    <>
      <SelectTopBar
        options={[
          { name: "커뮤니티", value: "community" },
          { name: "피드", value: "community/feed" },
        ]}
      />
      <div>{children}</div>
    </>
  );
}

