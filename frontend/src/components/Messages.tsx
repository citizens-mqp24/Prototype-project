import useFetchData from '../hooks/hookMessages.ts';
import LikeButton from "./LikeButton.tsx";

export default function Messages(){
        const apiUrl = 'http://localhost:8080/api/msg'; // URL to your API
        const { data, loading, error } = useFetchData(apiUrl);
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
                        <li className={"flex-row flex gap-5"} key={item.message_id}>
                            <strong>{item.user.name}:</strong> {item.message_text}
                            <div>
                                {item.likes}
                            </div>
                            <LikeButton msg={item}></LikeButton>
                        </li>
                    ))}
                </ul>
            </div>
        );
}