import {useSession} from "../../contexts/SessionContext.tsx";
import {Message} from "../../hooks/hookMessages.ts";
import {useState} from "react";

export default function LikeButton(props:{msg:Message,onLike?:() => void}) {
    const session = useSession();
    const [hasLiked,setHasLiked] = useState(false);
    function likePost(messageId :number | undefined) {
        if ((session.hasLoggedIn == false && session.info == undefined) || messageId == undefined) {
            return
        }
        if(props.onLike !== undefined) {
            props.onLike() // used for optimistic updates
        }
        setHasLiked(true);
        console.log(session)
        fetch(`/api/msg/likes/${messageId}`, {
            method: "POST",
            headers: {
                "Content-type": "Application/Json"
            },
            body: JSON.stringify({
                email: session.info?.userInfo.email
            })
        }).then()
    }
    console.log(session)
    if(session.hasLoggedIn == false && session.info == undefined) {
        return;
    }
    if(props.msg.message_id == undefined) {
        return
    }
    if (session.info?.userInfo == undefined) {
        return
    }
    if (session.info?.userInfo.likes != undefined) {
        const messageIds:(number | undefined)[] = session.info.userInfo.likes
            .map((likedMsg) => likedMsg.message_id);
        if (messageIds.includes(props.msg.message_id) || hasLiked===true) {
            return <div
                           className={"border p-3 rounded-2xl bg-blue-200"}>Like</div>
        }
    }

    return (
        <button onClick={() => likePost(props.msg.message_id)} className={"border p-3 rounded-2xl"}>Like</button>
    )
}