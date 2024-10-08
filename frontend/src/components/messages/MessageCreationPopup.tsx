import {useState} from "react";


export default function MessageCreationPopup(props:{hidden:boolean,onClose:()=>void,submitMsg:(txt:string) => void }) {
    const [msgTxt,setMsgTxt] = useState("");
    if(props.hidden) {
        return;
    }
    console.log("hello")

    return(
        <div className={"top-0 px-5 absolute w-full h-dvh flex flex-col"}>
            <div className={"flex flex-grow"}/>
            <div className={"bg-blue-300 rounded-2xl place-self-center w-full xl:w-1/2 h-fit flex flex-col gap-5 p-10"}>
                <button className={"w-fit p-2 rounded-2xl bg-neon-blue-200"} onClick={props.onClose}>Close</button>
                <textarea className={"border bg-blue-300 h-28 resize-none p-2 text-white  rounded-2xl"} value={msgTxt}
                          onChange={(e) => {
                              setMsgTxt(e.target.value)
                          }}/>

                <button
                    className={"border-2 rounded-2xl font-bold text-white bg-blue-700 w-full md:w-fit md:place-self-end p-2 md:px-10"}
                    onClick={() => props.submitMsg(msgTxt)}>Share
                </button>
            </div>
            <div className={"flex flex-grow"}/>
        </div>
    )
}