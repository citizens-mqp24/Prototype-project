import LikeButton from "./LikeButton.tsx";
import {Message} from "../hooks/hookMessages.ts";
import {useState} from "react";

export default function MessageDisplay(props:{message:Message}) {
    const [likes,setLikes] = useState(props.message.likes)
    function onLike() {
        if(likes === undefined) {
            return
        }
        setLikes(likes+1);
    }

    return (
        <li className={"flex-row flex gap-5"} key={props.message.message_id}>
            <strong>{props.message.user.name}:</strong> {props.message.message_text}
            <div>
                {likes}
            </div>
            <LikeButton onLike={onLike} msg={props.message}></LikeButton>
        </li>
    )
}