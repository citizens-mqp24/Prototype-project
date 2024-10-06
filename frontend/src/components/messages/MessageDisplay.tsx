import LikeButton from "./LikeButton.tsx";
import {Message} from "../../hooks/hookMessages.ts";
import {useState} from "react";
import {NewMessageButton} from "./NewMessageButton.tsx";
import {useSession} from "../../contexts/SessionContext.tsx";

export default function MessageDisplay(props:{message:Message}) {
    const [likes,setLikes] = useState(props.message.likes)
    console.log(props.message.comment_count)
    const [comment_count,setComment_count] = useState(props.message.comment_count)
    const session = useSession();
    function saveMessage(txt:string) {
        if(session.info == undefined || comment_count == undefined) {
            return; //TOOD error
        }
        const msg:Message = {
            message_text:txt,
            user: {
                name: session.info.userInfo.name,
                email: session.info.userInfo.email,
            },
            likes:0
        }
        setComment_count(comment_count+1)
        fetch(`/api/msg/comment/${props.message.message_id}`,{
            method:"POST",
            headers: {
                "Content-Type":"application/json"
            },
            body:JSON.stringify(msg)
        }).then()
    }

    function optomisticLike() {
        if(likes===undefined) {
            return
        }
        setLikes(likes+1)
    }

    return (
        <li className={"flex-row flex gap-5"} key={props.message.message_id}>
            <strong>{props.message.user.name}:</strong> {props.message.message_text}
            <div>
                {likes}
            </div>
            <div>Comments:{comment_count}</div>
            <LikeButton  onLike={() => optomisticLike} msg={props.message}></LikeButton>
            <NewMessageButton saveMessage={saveMessage}>
                comment
            </NewMessageButton>
        </li>
    )
}