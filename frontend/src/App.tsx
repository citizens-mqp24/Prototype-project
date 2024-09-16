import './App.css'
import {Outlet} from "react-router-dom";

function App() {
  return (
    <div >
        <div className="bg-blue-500 "> hello world</div>
        <Outlet />
    </div>
  )
}

export default App
