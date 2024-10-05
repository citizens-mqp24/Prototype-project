import useFetchData from '../hooks/hookMessages.ts';
import MessageDisplay from "../components/MessageDisplay.tsx";
import MessageCreationPopup from "../components/MessageCreationPopup.tsx";

export default function MessagesRoute(){
        const apiUrl = 'http://localhost:8080/api/msg'; // URL to your API
        const { data, loading, error,optimisticUpdate } = useFetchData(apiUrl);
        if (loading) {
            return <div>Loading...</div>;
        }

        if (error) {
            return <div>Error: {error}</div>;
        }


        return (
            <div>
                <ul>
                    {data.map(item => (
                        <MessageDisplay message={item}></MessageDisplay>
                    ))}
                </ul>
                <MessageCreationPopup onSend={optimisticUpdate}></MessageCreationPopup>
            </div>
        );
}