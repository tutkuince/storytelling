import { Outlet } from "react-router-dom";
import { Navbar } from "./shared/components/Navbar";
import { AuthenticationContext } from "./shared/state/context";

export const App = () => {
  return (
    <AuthenticationContext>
      <Navbar />
      <div className="container mt-3">
        <Outlet />
      </div>
    </AuthenticationContext>
  );
};
