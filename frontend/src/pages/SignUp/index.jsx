import axios from "axios";
import { useEffect, useMemo, useState } from "react";
import { signUp } from "./api";
import { Input } from "./components/input";

export function SignUp() {
  const [username, setUsername] = useState();
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [repeatPassword, setRepeatPassword] = useState();
  const [apiProgress, setApiProgress] = useState(false);
  const [successMessage, setSuccessMessage] = useState();
  const [errorMessage, setErrorMessage] = useState({});
  const [generalError, setGeneralError] = useState();

  useEffect(() => {
    setErrorMessage((lastErrors) => {
      return {
        ...lastErrors,
        username: undefined,
      };
    });
  }, [username]);

  useEffect(() => {
    setErrorMessage((lastErrors) => {
      return {
        ...lastErrors,
        email: undefined,
      };
    });
  }, [email]);

  useEffect(() => {
    setErrorMessage((lastErrors) => {
      return {
        ...lastErrors,
        password: undefined,
      };
    });
  }, [password]);

  const onSubmit = async (event) => {
    event.preventDefault();
    setApiProgress(true);
    setSuccessMessage();
    setGeneralError();

    try {
      const response = await signUp({
        //   username: username,
        //   email: email,
        //   password: password,
        username,
        email,
        password,
      });
      setSuccessMessage(response.data.message);
    } catch (error) {
      if (error.response?.data && error.response.data.status == 400) {
        setErrorMessage(error.response.data.validationErrors);
      } else {
        setGeneralError("Unexpected error occured. Please try again!");
      }
    } finally {
      setApiProgress(false);
    }
  };

  const passwordRepeatError = useMemo(() => {
    if (password && password !== repeatPassword) {
      return "Password mismatch";
    }
    return "";
  }, [password, repeatPassword]);

  return (
    <>
      <section className="vh-100">
        <div className="mask d-flex align-items center h-100 gradient-custom-3">
          <div className="container h-100">
            <div className="row d-flex justify-content-center align-items-center h-100">
              <div className="col-12 col-md-9 col-lg-7 col-xl-6">
                <div className="card">
                  <div className="card-header">
                    <h2 className="text-uppercase text-center py-3">
                      Create An Account
                    </h2>
                  </div>
                  <div className="card-body p-5">
                    <form onSubmit={onSubmit}>
                      <Input
                        id="username"
                        label="Username"
                        type="text"
                        error={errorMessage.username}
                        onChange={(event) => setUsername(event.target.value)}
                      />
                      <Input
                        id="email"
                        label="E-Mail"
                        type="email"
                        error={errorMessage.email}
                        onChange={(event) => setEmail(event.target.value)}
                      />
                      <Input
                        id="password"
                        label="Password"
                        type="password"
                        error={errorMessage.password}
                        onChange={(event) => setPassword(event.target.value)}
                      />
                      <Input
                        id="repeatPassword"
                        label="Repeat Your Password"
                        type="password"
                        error={passwordRepeatError}
                        onChange={(event) =>
                          setRepeatPassword(event.target.value)
                        }
                      />
                      {successMessage && (
                        <div className="alert alert-success" role="alert">
                          {successMessage}
                        </div>
                      )}
                      {generalError && (
                        <div className="alert alert-danger" role="alert">
                          {generalError}
                        </div>
                      )}
                      <div className="d-grid gap-2">
                        <button
                          type="submit"
                          className="btn btn-outline-dark"
                          disabled={
                            apiProgress ||
                            !password ||
                            password !== repeatPassword
                          }
                        >
                          {apiProgress && (
                            <span
                              className="spinner-border spinner-border-sm"
                              aria-hidden="true"
                            ></span>
                          )}
                          Register
                        </button>
                      </div>
                      <p className="text-center text-muted mt-5 mb-0">
                        Have already an account? &nbsp;
                        <a href="#!" className="fw-bold text-body">
                          <u>Login here</u>
                        </a>
                      </p>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}
