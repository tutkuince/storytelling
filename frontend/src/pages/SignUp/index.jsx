import axios from "axios";
import { useState } from "react";

export function SignUp() {
  const [username, setUsername] = useState();
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [repeatPassword, setRepeatPassword] = useState();

  const onSubmit = (event) => {
    event.preventDefault();
    axios.post("/api/v1/users", {
      //   username: username,
      //   email: email,
      //   password: password,
      username,
      email,
      password,
    });
  };

  return (
    <>
      <section className="vh-100">
        <div className="mask d-flex align-items center h-100 gradient-custom-3">
          <div className="container h-100">
            <div className="row d-flex justify-content-center align-items-center h-100">
              <div className="col-12 col-md-9 col-lg-7 col-xl-6">
                <div className="card">
                  <div className="card-body p-5">
                    <h2 className="text-uppercase text-center mb-5">
                      Create An Account
                    </h2>
                    <form onSubmit={onSubmit}>
                      <div className="form-outline mb-4">
                        <label className="form-label" htmlFor="username">
                          Username
                        </label>
                        <input
                          type="text"
                          id="username"
                          className="form-control form-control-lg"
                          onChange={(event) => setUsername(event.target.value)}
                        />
                      </div>
                      <div className="form-outline mb-4">
                        <label className="form-label" htmlFor="email">
                          E-Mail
                        </label>
                        <input
                          type="email"
                          id="email"
                          className="form-control form-control-lg"
                          onChange={(event) => setEmail(event.target.value)}
                        />
                      </div>
                      <div className="form-outline mb-4">
                        <label className="form-label" htmlFor="password">
                          Password
                        </label>
                        <input
                          type="password"
                          id="password"
                          className="form-control form-control-lg"
                          onChange={(event) => setPassword(event.target.value)}
                        />
                      </div>
                      <div className="form-outline mb-4">
                        <label className="form-label" htmlFor="repeatPassword">
                          Repeat Your Password
                        </label>
                        <input
                          type="password"
                          id="repeatPassword"
                          className="form-control form-control-lg"
                          onChange={(event) =>
                            setRepeatPassword(event.target.value)
                          }
                        />
                      </div>
                      <div className="d-grid gap-2">
                        <input
                          value="Register"
                          type="submit"
                          className="btn btn-outline-dark"
                          disabled={!password || password !== repeatPassword}
                        />
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
