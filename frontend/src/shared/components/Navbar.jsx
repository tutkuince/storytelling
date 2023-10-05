import { Link } from "react-router-dom";
import logo from "@/images/logo.png";
import { LanguageSelector } from "./LanguageSelector";
import { useTranslation } from "react-i18next";

export const Navbar = () => {
  const { t } = useTranslation();
  return (
    <nav className="navbar navbar-expand bg-dark">
      <div className="container-fluid">
        <Link className="navbar-brand text-white" to="/">
          <img src={logo} width={40} />
          Storytelling
        </Link>
        <ul className="navbar-nav">
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
          <li className="nav-item">
            <LanguageSelector />
          </li>
        </ul>
      </div>
    </nav>
  );
};
