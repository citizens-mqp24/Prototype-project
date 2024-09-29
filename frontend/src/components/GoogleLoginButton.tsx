import {useSession} from "../contexts/SessionContext.tsx";

export default function GoogleLoginButton() {
    const session = useSession();

    if(session.hasLoggedIn == false) {
        return (
            <div>
                {/*todo add a google icon*/}
                <a href={"/api/auth"}>
                    Login with Google
                </a>
            </div>
        )
    } else {
        //todo add logic
        return <a href={"/api/auth/logout"}>logout</a>
    }
}
