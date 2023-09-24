import React from "react";
import ReactDOM from "react-dom/client";
// import App from './App.jsx'
import { SignUp } from "./pages/SignUp/index.jsx";
import "./style.scss";
import "../src/locales/index.js";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <SignUp />
  </React.StrictMode>
);
