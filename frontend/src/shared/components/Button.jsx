import { Spinner } from "./Spinner";

export const Button = ({
  apiProgress,
  disabled,
  children,
  onClick,
  styleType = "outline-dark",
  type,
}) => {
  return (
    <button
      className={`btn btn-${styleType}`}
      type="submit"
      disabled={apiProgress || disabled}
      onClick={onClick}
      type={type}
    >
      {apiProgress && <Spinner sm="true" />}
      {children}
    </button>
  );
};
