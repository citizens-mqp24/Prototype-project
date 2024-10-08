import {useLocation, useNavigate} from "react-router-dom";

export default function BackButton() {
    const navigate = useNavigate();
    const location = useLocation();
    if(location.pathname === "/") {
        return
    }
    return(
        <button className={"bg-neon-blue-200 w-full"} onClick={() => navigate(-1)}>
            back
        </button>
    )
}