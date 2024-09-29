export default function ProfilePicture(props:{src:string,alt:string}) {
    return (
        <img  className={"w-16 h-16 rounded-full"} src={props.src}  alt={props.alt}></img>
    )
}