import './App.css'
import {Outlet} from "react-router-dom";
import GoogleLoginButton from "./components/GoogleLoginButton.tsx";

function App() {
  return (
    <div >
        <GoogleLoginButton>
        </GoogleLoginButton>
        <div className="bg-blue-500 "> hello world</div>
        <Outlet />
    </div>
  )
}

export default App
