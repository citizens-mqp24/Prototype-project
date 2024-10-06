import {useNavigate, useParams} from "react-router-dom";
import MessageDisplay from "../components/messages/MessageDisplay.tsx";
import useFetchData, {Message} from "../hooks/hookMessages.ts";
import {useQuery} from "@tanstack/react-query";

export default function SingleMessageRoute() {
    const { id } = useParams();
    const navigate = useNavigate();
    const { data, isLoading, error } = useQuery<Message>({
        queryKey:["fetch message"],
        queryFn: async () => {
            const res = await fetch(`/api/msg/${id}`)
            return res.json();
        }
    });

    if(id == undefined) {
        navigate("/")
    }
    if(error) {
        return error; // todo make this a component
    }
    if(isLoading) {
        return "loading..."; // todo make this a component
    }
    if(data === undefined) {
        return "could not find message";
    }
    console.log(data)
    return (
        <div>
            <ul>
                <MessageDisplay message={data}></MessageDisplay>
                {data.comments !== undefined ? data.comments.map(item => (
                    <MessageDisplay message={item}></MessageDisplay>
                )) :<></>}
            </ul>
        </div>
    )
}