import MessageCreationPopup from "./MessageCreationPopup.tsx";
import {ReactNode, useState} from "react";
import {useSession} from "../../contexts/SessionContext.tsx";
import {createPortal} from "react-dom";
declare global {
    interface Window {  saveFunc: (txt:string) => Promise<void>;}
}


export function NewMessageButton(props:{children:ReactNode,saveMessage:(txt:string) => Promise<void>}) {
    const [isHidden,setIsHidden] = useState(true);
    const session = useSession();
    if(session.hasLoggedIn == false) {
        return;
    }
    return(
        <>
            <button onClick={()=>  setIsHidden(false)}>
                {props.children}
            </button>
            {createPortal(
                <MessageCreationPopup hidden={isHidden} submitMsg={(txt) => {
                    window.saveFunc(txt).then(() => {setIsHidden(true)});
                }} onClose={() => setIsHidden(true)}></MessageCreationPopup>,
                document.body
            )}
        </>
    )
}