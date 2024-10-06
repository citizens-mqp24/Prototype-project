import useFetchData, {Message} from '../hooks/hookMessages.ts';
import MessageDisplay from "../components/messages/MessageDisplay.tsx";
import {useSession} from "../contexts/SessionContext.tsx";
import {NewMessageButton} from "../components/messages/NewMessageButton.tsx";

export default function MessagesRoute(){
        const apiUrl = 'http://localhost:8080/api/msg'; // URL to your API
        const { data, loading, error,optimisticUpdate } = useFetchData(apiUrl);
        const session = useSession();
        if (loading) {
            return <div>Loading...</div>;
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
    }
    // we add the filter here to filter out comments
    return (
        <div>
            <ul>
                {data.filter(msg => msg.mainMessage == null).map(item => (
                    <MessageDisplay message={item}></MessageDisplay>
                ))}
            </ul>
            <NewMessageButton saveMessage={saveMessage}>
                New Message
            </NewMessageButton>
        </div>
    );
}