import {useSession} from "../../contexts/SessionContext.tsx";
import {Message} from "../../hooks/hookMessages.ts";
import {useState} from "react";
import LikedIcon from "../icons/LikedIcon.tsx";
import SleepyIcon from "../icons/SleepyIcon.tsx";

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
            return <div className={"w-fit py-1 px-1 rounded-full bg-neon-pink-100"}>
                <LikedIcon/>
            </div>
        }
    }

    return (
        <button onClick={() => likePost(props.msg.message_id)}
                className={"bg-neon-pink-200 hover:bg-neon-pink-100 w-fit py-1 px-1 rounded-full rounded-2xl"}>
            <SleepyIcon/>
        </button>
    )
}