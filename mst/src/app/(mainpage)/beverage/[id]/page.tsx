interface BeverageParams {
  params: { id: string };
}

function BeverageDetail({ params: { id } }: BeverageParams) {
  return <div>{id}</div>;
}

export default BeverageDetail;
