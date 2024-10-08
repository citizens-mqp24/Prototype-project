import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import {createBrowserRouter, createRoutesFromElements, Route, RouterProvider} from "react-router-dom";
import TestRoute from "./routes/TestRoute.tsx";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {SessionContextProvider} from "./contexts/SessionContext.tsx";
import MessagesRoute from "./routes/MessagesRoute.tsx";
import SingleMessageRoute from "./routes/SingleMessageRoute.tsx";
import MessageSaveContextProvider from "./contexts/SaveMessageContext.tsx";


const router = createBrowserRouter(
    createRoutesFromElements(
        <Route path="/" element={<App />}>
            <Route path={""} element={<MessagesRoute/>}/>
            <Route path={"/msg/:id"} element={<SingleMessageRoute/>}/>
            <Route path={"/test"} element={<TestRoute></TestRoute>}/>
        </Route>
    )
);

const queryClient = new QueryClient()

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <QueryClientProvider client={queryClient}>
          <SessionContextProvider>
              <MessageSaveContextProvider>
                 <RouterProvider router={router}></RouterProvider>
              </MessageSaveContextProvider>
          </SessionContextProvider>
      </QueryClientProvider>
  </StrictMode>,
)
