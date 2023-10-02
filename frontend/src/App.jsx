import { Outlet } from "react-router-dom";
import { Navbar } from "./shared/components/Navbar";

export const App = () => {
  return (
    <>
      <Navbar />
      <div className="container mt-3">
        <Outlet />
      </div>
    </>
  );
};
