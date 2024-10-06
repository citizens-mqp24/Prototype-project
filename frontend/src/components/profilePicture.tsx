export default function ProfilePicture(props:{src:string | undefined,alt:string}) {
    return (
        <img className={"w-16 h-16 border border-black rounded-full"} src={props.src}  alt={props.alt}></img>
    )
}