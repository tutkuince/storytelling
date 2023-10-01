import {
  createBrowserRouter
} from "react-router-dom";
import { Home } from "../pages/Home/index.jsx";
import { SignUp } from "../pages/SignUp/index.jsx";
import { App } from "../App.jsx";

export default createBrowserRouter([
    {
      path: "/",
      Component: App,
      children: [
        {
          path: "*",
          index: true,
          Component: Home
        },
        {
          path: "/signUp",
          Component: SignUp
        }
      ]
    }
  ]);