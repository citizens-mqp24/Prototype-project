import MessageCreationPopup from "./MessageCreationPopup.tsx";
import {ReactNode, useState} from "react";
import {useSession} from "../../contexts/SessionContext.tsx";

export function NewMessageButton(props:{children:ReactNode,saveMessage:(txt:string) => void}) {
    const [isHidden,setIsHidden] = useState(true);
    const session = useSession();
    if(session.hasLoggedIn == false) {
        return;
    }
    return(
        <>
            <button onClick={()=> setIsHidden(false)}>
                {props.children}
            </button>
            <MessageCreationPopup hidden={isHidden} submitMsg={(txt) => {
                props.saveMessage(txt);
            }} onClose={() => setIsHidden(true)}></MessageCreationPopup></>

    )
}