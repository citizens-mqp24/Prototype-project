import SpeechBubbleSvg from "../icons/SpeechBubbleSvg.tsx";
import SleepyIcon from "../icons/SleepyIcon.tsx";

export default function LoadingMessageDisplay() {
    return (
        <div className={"flex-col p-2 border-neon-blue-100 border bg-neon-blue-200 pt-5 flex gap-5"}>
            <div className={"flex flex-row gap-2"}>
                <div className={"bg-slate-200 w-12 h-12 rounded-full animate-pulse "}></div>
                <div className={"flex flex-col gap-2"}>
                    <div className={"rounded-2xl animate-pulse bg-slate-200 w-20 h-4"}></div>
                    <div className={"rounded-2xl bg-slate-200 animate-pulse w-40 h-4"}>
                    </div>
                </div>
            </div>
            <div className={"flex flex-row gap-5"}>
                <div className={"flex flex-row gap-2"}>
                    <div className={"animate-pulse bg-neon-pink-200 hover:bg-neon-pink-100 w-fit py-1 px-1 rounded-full"}>
                        <SleepyIcon/>
                    </div>
                    <div className={"animate-pulse bg-slate-200 place-self-center rounded-2xl w-5 h-5"}>
                    </div>
                </div>
                <div className={"flex flex-row gap-2 animate-pulse"}>
                    <SpeechBubbleSvg/>
                    <div className={"animate-pulse bg-slate-200 place-self-center rounded-2xl w-5 h-5"}>
                    </div>
                </div>
            </div>
        </div>
    )
}