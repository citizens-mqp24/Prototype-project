import LikeButton from "./LikeButton.tsx";
import {Message} from "../../hooks/hookMessages.ts";
import {useState} from "react";
import SpeechBubbleSvg from "../icons/SpeechBubbleSvg.tsx";
import {Link} from "react-router-dom";

export default function MessageDisplay(props:{message:Message,showFull:boolean}) {
    const [likes,setLikes] = useState(props.message.likes)
    console.log(props.showFull)
    const [showFull,setShowFull] = useState(props.showFull)
    function optomisticLike() {
        console.log(likes);

        if(likes===undefined) {
            return
        }
        setLikes(likes+1)
    }
    return (
        <div className={"flex-col p-2 border-neon-blue-100 border bg-neon-blue-200 pt-5 flex gap-5"}
             key={props.message.message_id}>
            <div className={"flex flex-row gap-2"}>
                <img className={"w-12 h-12 rounded-full "} src={props.message.user.picture}
                     alt={props.message.user.name}></img>
                <div>
                    <strong>{props.message.user.name}:</strong>
                    <div className={"flex flex-col"}>
                        {props.message.message_text.length > 255 && showFull ? <button className={"hover:underline"} onClick={() => setShowFull(false)}>Show less</button> : <></>}
                        <div className={"max-h-96 overflow-scroll"}>
                        {showFull ?
                            <div>
                                {props.message.message_text}
                            </div>
                            :
                            <div className={"flex-col flex"}>
                                {props.message.message_text.substring(0,255)}
                                {props.message.message_text.length > 255 ?
                                    <button className={"hover:underline"} onClick={() => setShowFull(true)}>show
                                        full</button> :
                                <></>}
                            </div>
                        }
                        </div>
                    </div>
                </div>
            </div>
            <div className={"flex flex-row gap-5"}>
                <div className={"flex flex-row gap-2"}>
                    <LikeButton onLike={optomisticLike} msg={props.message}></LikeButton>
                    <div className={"place-self-center"}>
                        {likes}
                    </div>
                </div>
                <div className={"flex flex-row gap-2"}>
                    <Link to={`/msg/${props.message.message_id}`}>
                        <SpeechBubbleSvg/>
                    </Link>
                    <div className={"place-self-center"}>
                        {props.message.comment_count}
                    </div>
                </div>
            </div>
        </div>
    )
}