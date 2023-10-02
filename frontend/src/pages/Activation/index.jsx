import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { activateUser } from "./api";

export const Activation = () => {
  const { token } = useParams();
  const [apiProgress, setApiProgress] = useState();
  const [successMessage, setSuccessMessage] = useState();
  const [errorMessage, setErrorMessage] = useState();

  useEffect(() => {
    async function activate() {
      setApiProgress(true);
      try {
        activateUser(token);
      } catch (axiosError) {
        setErrorMessage(axiosError.response.data.message);
      } finally {
        setApiProgress(false);
      }
    }
    activate();
  }, []);

  return (
    <>
      {apiProgress && (
        <span
          className="spinner-border spinner-border"
          aria-hidden="true"
        ></span>
      )}
      {successMessage && (
        <div className="alert alert-success" role="alert">
          {successMessage}
        </div>
      )}
      {errorMessage && (
        <div className="alert alert-danger" role="alert">
          {errorMessage}
        </div>
      )}
    </>
  );
};
