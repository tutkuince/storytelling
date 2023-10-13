import { useState } from "react";
import { Login } from "./pages/Login";
import { Navbar } from "./shared/components/Navbar";

export const App = () => {
  const [authState, setAuthState] = useState({
    id: 0,
  });

  const onLoginSuccess = (data) => {
    setAuthState(data);
  };
  return (
    <>
      <Navbar authState={authState} />
      <div className="container mt-3">
        {/* <Outlet /> */}
        <Login onLoginSuccess={onLoginSuccess} />
      </div>
    </>
  );
};
