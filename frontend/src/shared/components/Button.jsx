import { Spinner } from "./Spinner";

export const Button = ({
  apiProgress,
  disabled,
  children,
  onClick,
  styleType = "outline-dark",
}) => {
  return (
    <button
      className={`btn btn-${styleType}`}
      type="submit"
      disabled={apiProgress || disabled}
      onClick={onClick}
    >
      {apiProgress && <Spinner sm="true" />}
      {children}
    </button>
  );
};
