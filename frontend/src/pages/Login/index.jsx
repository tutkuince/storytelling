import { Alert } from "@/shared/components/Alert";
import { Button } from "@/shared/components/Button";
import { Input } from "@/shared/components/Input";
import { AuthContext, useAuthDispatch } from "@/shared/state/context";
import { useContext, useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { Link, useNavigate } from "react-router-dom";
import { login } from "./api";

export const Login = () => {
  const authState = useContext(AuthContext);
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [apiProgress, setApiProgress] = useState();
  const [errors, setErrors] = useState();
  const [generalError, setGeneralError] = useState();
  const { t } = useTranslation();
  const navigate = useNavigate();
  const dispatch = useAuthDispatch();

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        email: undefined,
      };
    });
  }, [email]);

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        password: undefined,
      };
    });
  }, [password]);

  const onSubmit = async (event) => {
    event.preventDefault();
    setGeneralError();
    setApiProgress(true);

    try {
      const response = await login({ email, password });
      dispatch({ type: "login-success", data: response.data });
      navigate("/");
    } catch (axiosError) {
      if (axiosError.response?.data) {
        if (axiosError.response.data.status === 400) {
          setErrors(axiosError.response.data.validationErrors);
        } else {
          setGeneralError(axiosError.response.data.message);
        }
      } else {
        setGeneralError(t("genericError"));
      }
    } finally {
      setApiProgress(false);
    }
  };

  return (
    <>
      <section className="vh-100">
        <div className="container-fluid h-custom">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-md-9 col-lg-6 col-xl-5">
              <img
                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                className="img-fluid"
                alt="Sample image"
              />
            </div>
            <div className="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
              <form onSubmit={onSubmit}>
                <div className="form-outline mb-4">
                  <label className="form-label" htmlFor="email">
                    {t("email")}
                  </label>
                  <Input
                    type="email"
                    id="email"
                    className="form-control form-control-lg"
                    placeholder="Enter a valid email address"
                    onChange={(event) => setEmail(event.target.value)}
                  />
                </div>

                <div className="form-outline mb-3">
                  <label className="form-label" htmlFor="password">
                    {t("password")}
                  </label>
                  <Input
                    type="password"
                    id="password"
                    className="form-control form-control-lg"
                    placeholder="Enter password"
                    onChange={(event) => setPassword(event.target.value)}
                  />
                </div>

                <div className="d-flex justify-content-between align-items-center">
                  <div className="form-check mb-0">
                    <input
                      className="form-check-input me-2"
                      type="checkbox"
                      value=""
                      id="form2Example3"
                    />
                    <label className="form-check-label" htmlFor="form2Example3">
                      Remember me
                    </label>
                  </div>
                  <a href="#!" className="text-body">
                    Forgot password?
                  </a>
                </div>
                {generalError && (
                  <Alert styleType="danger">{generalError}</Alert>
                )}
                <div className="text-center text-lg-start mt-4 pt-2 d-grid gap-2">
                  <Button
                    disabled={!email || !password}
                    apiProgress={apiProgress}
                  >
                    {t("login")}
                  </Button>
                  <p className="small fw-bold mt-2 pt-1 mb-0">
                    {"Don't have an account?"}&nbsp;
                    <Link to={"/signUp"} className="link-danger">
                      {t("signUp")}
                    </Link>
                  </p>
                </div>
              </form>
            </div>
          </div>
        </div>
      </section>
    </>
  );
};
