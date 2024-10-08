import ProfilePicture from "./profilePicture.tsx";
import GoogleLoginButton from "./GoogleLoginButton.tsx";
import {useSession} from "../contexts/SessionContext.tsx";
import {NewMessageButton} from "./messages/NewMessageButton.tsx";
import {useSaveMessageContext} from "../contexts/SaveMessageContext.tsx";
import BackButton from "./BackButton.tsx";

export default function SideBar() {
    const session = useSession();
    const msgSaveFun = useSaveMessageContext();

    return(
        <div className={"flex flex-col border-r border-neon-blue-100 h-screen min-w-fit gap-5 bg-neon-blue-300"}>
            <div className={"flex p-2 flex-col sm:flex-row gap-5 place-items-center"}>
                {session.hasLoggedIn && session.info !== undefined ?
                    <ProfilePicture src={session.info?.userInfo.picture} alt={"your profile picture"}/>
                    : <></>}
                <GoogleLoginButton/>
            </div>
            <NewMessageButton saveMessage={msgSaveFun.func}>
                <div className={"bg-neon-blue-200 w-full "}> New Message </div>
            </NewMessageButton>
            <BackButton>

            </BackButton>
        </div>
    )
}