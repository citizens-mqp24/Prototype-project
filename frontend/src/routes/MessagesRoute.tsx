import useFetchData, {Message} from '../hooks/hookMessages.ts';
import {useSession} from "../contexts/SessionContext.tsx";
import {MessageList} from "../components/messages/MessageList.tsx";
import LoadingMessageDisplay from "../components/messages/LoadingMessageDisplay.tsx";


export default function MessagesRoute(){
    const apiUrl = 'http://localhost:8080/api/msg'; // URL to your API
    const { data, loading, error,optimisticUpdate,fetchData } = useFetchData(apiUrl);
    const session = useSession();
    if (loading) {
        return (
            <div className={"flex flex-col"}>
                <LoadingMessageDisplay/>
                <LoadingMessageDisplay/>
                <LoadingMessageDisplay/>
                <LoadingMessageDisplay/>
                <LoadingMessageDisplay/>
                <LoadingMessageDisplay/>
                <LoadingMessageDisplay/>
                <LoadingMessageDisplay/>
            </div>
        )
    }

    if (error) {
        return <div>Error: {error}</div>;
    }
    async function saveMessage(txt:string) {
        if(session.info == undefined) {
            return; //TOOD error
        }
        const msg:Message = {
            mainMessage: null,
            message_text:txt,
            user: {
                name: session.info.userInfo.name,
                email: session.info.userInfo.email,
                picture: session.info.userInfo.picture
            },
            likes:0
        }
        optimisticUpdate(msg);
        await fetch("/api/msg",{
            method:"POST",
            headers: {
                "Content-Type":"application/json"
            },
            body:JSON.stringify(msg)
        })
        fetchData().then()
    }
    window.saveFunc = saveMessage;
    // we add the filter here to filter out comments
    return(
        <MessageList messages={data.filter(msg => msg.mainMessage == null)}/>
    );
}