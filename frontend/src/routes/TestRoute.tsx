import {useQuery} from "@tanstack/react-query";

export default function TestRoute() {
    const { isPending, error, data, isFetching } = useQuery({
        queryKey: ['users'],
        queryFn: async () => {
            const response = await fetch(
                '/api/users',
            )
            return await response.json()
        },
    })

    if (isPending) return 'Loading...'

    console.log(data)

    if (error) return 'An error has occurred: ' + error.message

    return (
        <div>
            {data.map((user) => (
                <>
                    <p>{user.user_id}</p>
                    <p>{user.name}</p>
                </>
            ))}
            <div>{isFetching ? 'Updating...' : ''}</div>
        </div>
    )
}