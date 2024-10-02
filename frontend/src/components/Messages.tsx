import useFetchData from '../hooks/hookMessages.ts';

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
                        <li key={item.message_id}>
                            {item.message_text}
                        </li>
                    ))}
                </ul>
            </div>
        );
}