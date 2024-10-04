import {useSession} from "../contexts/SessionContext.tsx";
import {Message} from "../hooks/hookMessages.ts";

export default function LikeButton(props:{msg:Message}) {
    const session = useSession();
    function likePost(messageId :number | undefined) {
        if ((session.hasLoggedIn == false && session.info == undefined) || messageId == undefined) {
            return
        }
        fetch(`/api/msg/likes/${messageId}`, {
            method: "POST",
            headers: {
                "Content-type": "Application/Json"
            },
            body: JSON.stringify({
                email: session.info?.email
            })
        }).then()
    }
    if(session.hasLoggedIn == false) {
        return;
    }
    if (props.msg.usersLiked != undefined) {
        const userEmails = props.msg.usersLiked.map(usr => usr.email)
        if(userEmails.includes(session.info?.email)) {
            return <div
                           className={"border p-3 rounded-2xl bg-blue-200"}>Like</div>
        }
    }

    return (
        <button onClick={() => likePost(props.msg.message_id)} className={"border p-3 rounded-2xl"}>Like</button>
    )
}