import './App.css'
import {Outlet} from "react-router-dom";
import GoogleLoginButton from "./components/GoogleLoginButton.tsx";
import ProfilePicture from "./components/profilePicture.tsx";
import {useSession} from "./contexts/SessionContext.tsx";

function App() {
    const session = useSession();

  return (
    <div >
        {session.hasLoggedIn  && session.info !== undefined? <ProfilePicture src={session.info?.picture} alt={"your profile picture"}/> : <></>}

        <GoogleLoginButton>
        </GoogleLoginButton>
        <div className="bg-blue-500 "> hello world</div>
        <Outlet />
    </div>
  )
}

export default App
