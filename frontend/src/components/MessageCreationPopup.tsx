import {useState} from "react";

export default function MessageCreationPopup() {
    const [msg,setMsg] = useState("");
    return(
        <div>
            <input value={msg} onChange={(e) => {setMsg(e.target.value)}}/>

            <button></button>
        </div>
    )
}