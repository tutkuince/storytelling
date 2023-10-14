import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "@popperjs/core";
import "bootstrap";
import { useTranslation } from "react-i18next";

export const LanguageSelector = () => {
  let result = "Language";
  const { i18n } = useTranslation();
  const onSelectLanguage = (language) => {
    i18n.changeLanguage(language);
    localStorage.setItem("language", language);
    result = language;
  };
  return (
    <>
      <div className="dropdown my-1 p-0">
        <button
          className="btn btn-secondary dropdown-toggle"
          type="button"
          id="dropdownMenuButton1"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          {result}
        </button>
        <ul className="dropdown-menu" aria-labelledby="dropdownMenuButton1">
          <li>
            <img
              src="https://flagcdn.com/24x18/tr.png"
              role="button"
              width="24"
              height="18"
              alt="Turkce"
              onClick={() => onSelectLanguage("tr")}
            ></img>
          </li>
          <li>
            <img
              src="https://flagcdn.com/24x18/gb.png"
              role="button"
              width="24"
              height="18"
              alt="English"
              onClick={() => onSelectLanguage("en")}
            ></img>
          </li>
        </ul>
      </div>
    </>
  );
};
