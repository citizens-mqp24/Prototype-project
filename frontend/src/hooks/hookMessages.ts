import {useEffect, useState} from 'react';

interface Message {
    id: number;
    user_id: number;
    message_text: string;
}

function useFetchData(apiUrl: string) {
    const [data, setData] = useState<Message[]>([]); // Holds the fetched data
    const [loading, setLoading] = useState<boolean>(true); // Tracks whether loading or not
    const [error, setError] = useState<string | null>(null); // Holds errors

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(apiUrl);
                if (!response.ok) {
                    throw new Error(`Error fetching data: ${response.statusText}`);
                }
                const result = await response.json();
                setData(result);
            } catch (error) {
                if (error instanceof Error) {
                    console.error(error.message);
                    setError(error.message); //if there are any errors will store errors
                } else {
                    console.error("An unknown error occurred.");
                    console.error(error);
                }
            } finally {
                setLoading(false); //Will set loading state to not loading anymore regardless of errors or no errors
            }
        };
        fetchData();
    }, [apiUrl]);

    return {data, loading, error};
}

export default useFetchData;