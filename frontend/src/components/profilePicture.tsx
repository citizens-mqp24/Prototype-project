export default function ProfilePicture(props:{src:string | undefined,alt:string}) {
    if(props.src === undefined) {
        return
    }
    return (
        <img className={"w-16 h-16 border border-black rounded-full"} src={props.src}  alt={props.alt}></img>
    )
}