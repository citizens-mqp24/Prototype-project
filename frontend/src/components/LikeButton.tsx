import {useSession} from "../contexts/SessionContext.tsx";
import {Message} from "../hooks/hookMessages.ts";

export default function LikeButton(props:{msg:Message}) {
    const session = useSession();
    function likePost(messageId :number | undefined) {
        if ((session.hasLoggedIn == false && session.info == undefined) || messageId == undefined) {
            return
        }
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
        if (messageIds.includes(props.msg.message_id)) {
            return <div
                           className={"border p-3 rounded-2xl bg-blue-200"}>Like</div>
        }
    }

    return (
        <button onClick={() => likePost(props.msg.message_id)} className={"border p-3 rounded-2xl"}>Like</button>
    )
}