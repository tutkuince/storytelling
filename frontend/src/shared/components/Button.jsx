import { Spinner } from "./Spinner";

export const Button = ({ apiProgress, disabled, children }) => {
  return (
    <button
      type="submit"
      className="btn btn-outline-dark"
      disabled={apiProgress || disabled}
    >
      {apiProgress && <Spinner sm="true" />}
      {children}
    </button>
  );
};
