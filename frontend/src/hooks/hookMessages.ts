import {useEffect, useState} from 'react';

interface Message {
    message_id: number;
    user_id: number;
    message_text: string;
}

function useFetchData(apiUrl: string) {
    const [data, setData] = useState<Message[]>([]); // Holds the fetched data
    const [loading, setLoading] = useState<boolean>(true); // Tracks loading state
    const [error, setError] = useState<string | null>(null); // Tracks errors

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(apiUrl);
                if (!response.ok) {
                    throw new Error(`Error fetching data: ${response.statusText}`);
                }
                const result = await response.json();
                setData(result);
            } catch (err: any) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [apiUrl]);

    return { data, loading, error };
}

export default useFetchData;