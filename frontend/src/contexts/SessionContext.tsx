import {useQuery} from "@tanstack/react-query";
import {createContext, useContext, useState} from "react";


interface UserInfo {
    id: string;
    name: string;
    email: string;
    verified: boolean;
    family_name: string;
    picture:string
}

interface session {
    info:UserInfo | undefined
    hasLoggedIn: boolean
    setHasLoggedIn: (hasLoggedIn: boolean) => void,
    isError: boolean,
    isLoading: boolean,
    error: Error | null
}

const SessionContext = createContext<session>({
    error: null,
    isError: false,
    isLoading: true,
    info: undefined,
    hasLoggedIn: false,
    setHasLoggedIn: () => {}
})
export const useSession = () => useContext(SessionContext);

export function SessionContextProvider (props: { children: React.ReactNode }) {
    const [hasLoggedIn, setHasLoggedIn] = useState(false);
    const {isError,isPending,data,error} = useQuery<UserInfo>({
        queryKey: ["user info",hasLoggedIn],
        queryFn: async () => {
            const res = await fetch('/api/users/info',{
                method: "POST",
            })
            if (res.status === 200) {
                console.log("logged in")
                setHasLoggedIn(true);
            } else {
                console.log(res)
                setHasLoggedIn(false)
            }
            return res.json()
        }
    })

    return(
        <SessionContext.Provider value={{
            info: data,
            hasLoggedIn,
            setHasLoggedIn: (hasLoggedIn) => {setHasLoggedIn(hasLoggedIn)},
            isError:isError,
            isLoading:isPending,
            error:error
        }}>
            {props.children}
        </SessionContext.Provider>
    )
}