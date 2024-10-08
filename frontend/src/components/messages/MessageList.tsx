import {Message} from "../../hooks/hookMessages.ts";
import MessageDisplay from "./MessageDisplay.tsx";

export function MessageList(props:{messages:Message[]}) {
    return (
        <div className={"flex h-full w-full flex-col bg-neon-blue-300 overflow-auto"}>
            {props.messages.map(item => (
                <MessageDisplay showFull={false} message={item}></MessageDisplay>
            ))}
        </div>
    )
}