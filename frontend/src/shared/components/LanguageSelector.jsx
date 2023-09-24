import { useTranslation } from "react-i18next";

export const LanguageSelector = () => {
  const { i18n } = useTranslation();
  const onSelectLanguage = (language) => {
    i18n.changeLanguage(language);
    localStorage.setItem("language", language);
  };
  return (
    <>
      <img
        src="https://flagcdn.com/24x18/tr.png"
        role="button"
        width="24"
        height="18"
        alt="Turkce"
        onClick={() => onSelectLanguage("tr")}
      ></img>
      &nbsp;
      <img
        src="https://flagcdn.com/24x18/gb.png"
        role="button"
        width="24"
        height="18"
        alt="English"
        onClick={() => onSelectLanguage("en")}
      ></img>
    </>
  );
};
