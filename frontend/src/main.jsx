import React from "react";
import ReactDOM from "react-dom/client";
// import App from './App.jsx'
import { RouterProvider } from "react-router-dom";
import "../src/locales/index.js";
import router from "./router";
import "./style.scss";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
