import './App.css'
import {Outlet} from "react-router-dom";
import GoogleLoginButton from "./components/GoogleLoginButton.tsx";
import ProfilePicture from "./components/profilePicture.tsx";
import {useSession} from "./contexts/SessionContext.tsx";
import Messages from "./components/Messages.tsx";

function App() {
    const session = useSession();

  return (
    <div >
        <div className={"flex flex-row place-items-center gap-5 p-2"}>
            {session.hasLoggedIn  && session.info !== undefined? <ProfilePicture src={session.info?.picture} alt={"your profile picture"}/> : <></>}

            <GoogleLoginButton/>
        </div>
        <div className="bg-blue-500 "> hello world</div>
        <Messages/>
        <Outlet />
    </div>
  )
}

export default App
