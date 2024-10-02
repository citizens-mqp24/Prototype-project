import {useState} from "react";
import {Message} from "../hooks/hookMessages.ts";
import {useSession} from "../contexts/SessionContext.tsx";

export default function MessageCreationPopup() {
    const [msgTxt,setMsgTxt] = useState("");
    const session = useSession()
    async function saveMessage() {
        if(session.info == undefined) {
            return; //TOOD error
        }
        const msg:Message = {
            message_text:msgTxt,
            user: {
                email: session.info.email,
            }
        }

        await fetch("/api/msg",{
            method:"POST",
            headers: {
                "Content-Type":"application/json"
            },
            body:JSON.stringify(msg)
        })
    }

    return(
        <div>
            <input className={"border border-gray-400"} value={msgTxt} onChange={(e) => {setMsgTxt(e.target.value)}}/>

            <button onClick={() => saveMessage().then()}>Submit</button>
        </div>
    )
}