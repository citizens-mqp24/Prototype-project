import {createContext, ReactNode, useContext, useRef} from "react";

export interface SaveContext {
    func:(txt:string) => Promise<void>
    setFunc:(func:(txt:string)=> Promise<void>) => void
}

const SaveMessageContext = createContext<SaveContext>({
    func(txt: string): Promise<void> {
        console.log(txt)
        return Promise.resolve()
    }, setFunc(func: (txt: string) => Promise<void>): void {
        console.log(func)
    }
})

export const useSaveMessageContext = () => useContext(SaveMessageContext);

//this is to make up for the fact that the backend has a separate route for coments rather than msgs
export default function MessageSaveContextProvider(props:{children:ReactNode}) {
    const defaultFunc = async () => {
        console.log("please set the save function")
    }
    const saveFunc = useRef<(txt:string) => Promise<void>>(defaultFunc)
    const setFunc = (func: (txt: string) => Promise<void>) => {
        console.log(func)
       saveFunc.current = func;
        console.log(saveFunc.current)
    }
    return(
        <SaveMessageContext.Provider value={{func:saveFunc.current,setFunc:setFunc}}>
            {props.children}
        </SaveMessageContext.Provider>
    )
}