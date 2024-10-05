import {useQuery} from "@tanstack/react-query";
import {createContext, useContext, useState} from "react";
import {User} from "../hooks/hookMessages.ts";


interface GoogleUserInfo {
    id: string;
    name: string;
    email: string;
    verified: boolean;
    family_name: string;
    picture:string
}

interface SessionInfo {
    googleInfo:GoogleUserInfo,
    userInfo: User
}



interface session {
    info:SessionInfo | undefined
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
    const {isError,isPending,data,error} = useQuery<SessionInfo>({
        queryKey: ["user info",hasLoggedIn],
        queryFn: async () => {
            const res = await fetch('/api/users/session',{
                method: "POST",
            })
            if (res.status === 200) {
                console.log("logged in")
                setHasLoggedIn(true);
            } else {
                console.log(res)
                setHasLoggedIn(false)
            }
            const sessionInfo:SessionInfo = await res.json()
            console.log(sessionInfo)
            return sessionInfo
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