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
        <div className={"bg-blue-300 rounded-2xl w-full xl:w-1/2 h-fit flex flex-col gap-5 p-10"}>
            <textarea className={"border bg-blue-300 h-28 resize-none p-2 text-white  rounded-2xl"} value={msgTxt} onChange={(e) => {setMsgTxt(e.target.value)}}/>

            <button className={"border-2 rounded-2xl font-bold text-white bg-blue-700 w-full md:w-fit md:place-self-end p-2 md:px-10"} onClick={() => saveMessage().then()}>Share</button>
        </div>
    )
}