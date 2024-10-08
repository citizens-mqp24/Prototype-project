import {useNavigate, useParams} from "react-router-dom";
import MessageDisplay from "../components/messages/MessageDisplay.tsx";
import  {Message} from "../hooks/hookMessages.ts";
import { useQuery, useQueryClient} from "@tanstack/react-query";
import {useState} from "react";
import {useSession} from "../contexts/SessionContext.tsx";
import {MessageList} from "../components/messages/MessageList.tsx";

export default function SingleMessageRoute() {
    const { id } = useParams();
    const navigate = useNavigate();
    const session = useSession();
    const queryClient = useQueryClient();
    const [comment_count,setComment_count] = useState(0);
    async function getMainMsg() {
        const res = await fetch(`/api/msg/${id}`)
        const msg:Message = await res.json();
        if(msg.comment_count != undefined) {
            setComment_count(msg.comment_count)
        }
        return msg
    }
    const { data, isLoading, error } = useQuery<Message>({
        queryKey:["fetch message"],
        queryFn: getMainMsg
    });


    if(id == undefined) {
        navigate("/")
    }
    if(error) {
        return <div>Error loading component</div>; // todo make this a component
    }
    if(isLoading) {
        return <div>Loading..</div>; // todo make this a component
    }
    if(data === undefined) {
        return <div>Could Not find message</div>;
    }
    async function saveMessage(txt:string) {
        if(session.info == undefined || comment_count == undefined) {
            return; //TOOD error
        }
        const msg:Message = {
            mainMessage: null,
            message_text:txt,
            user: {
                name: session.info.userInfo.name,
                email: session.info.userInfo.email,
            },
            likes:0
        }
        if(data == undefined) {
            return;
        }
        await fetch(`/api/msg/comment/${data.message_id}`,{
            method:"POST",
            headers: {
                "Content-Type":"application/json"
            },
            body:JSON.stringify(msg)
        }).then(() => {
            queryClient.invalidateQueries({
                queryKey:["fetch message"]
            })
        })
    }
    window.saveFunc = saveMessage;
    return (
        <div className={"flex flex-col w-full"}>
            <MessageDisplay showFull={true} message={data}></MessageDisplay>
            {data.comments !== undefined ?
            <MessageList messages={data.comments}/> : <></>}
        </div>
    )
}