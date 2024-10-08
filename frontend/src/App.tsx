import {Outlet} from "react-router-dom";
import SideBar from "./components/SideBar.tsx";

function App() {

  return (
    <div className={"bg-neon-blue-300 h-dvh flex flex-row"} >
            <SideBar/>
            <Outlet />
    </div>
  )
}

export default App
