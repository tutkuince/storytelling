import { Link } from "react-router-dom";
import logo from "@/images/logo.png";
import { LanguageSelector } from "./LanguageSelector";
import { useTranslation } from "react-i18next";
import { useContext } from "react";
import { AuthContext } from "../state/context";

export const Navbar = () => {
  const { t } = useTranslation();
  const authState = useContext(AuthContext);
  const onClickLogout = () => {
    authState.onLogoutSuccess();
  };
  return (
    <nav className="navbar navbar-expand bg-dark">
      <div className="container-fluid">
        <Link className="navbar-brand text-white" to="/">
          <img src={logo} width={40} />
          Storytelling
        </Link>
        <ul className="navbar-nav">
          {authState.id === 0 && (
            <>
              <li className="nav-item align-middle">
                <Link to="/login" className="nav-link text-white px-2">
                  {t("login")}
                </Link>
              </li>
              <li className="nav-item align-middle">
                <Link to="/signup" className="nav-link text-white px-2">
                  {t("signUp")}
                </Link>
              </li>
            </>
          )}

          {authState.id > 0 && (
            <>
              <li className="nav-item align-middle">
                <Link
                  to={`/user/${authState.id}`}
                  className="nav-link text-white px-2"
                >
                  My Profile
                </Link>
              </li>
              <li className="nav-item align-middle">
                <span
                  className="nav-link text-white px-2"
                  role="button"
                  onClick={onClickLogout}
                >
                  Logout
                </span>
              </li>
            </>
          )}
          <li className="nav-item">
            <LanguageSelector />
          </li>
        </ul>
      </div>
    </nav>
  );
};
