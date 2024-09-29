import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import {createBrowserRouter, createRoutesFromElements, Route, RouterProvider} from "react-router-dom";
import TestRoute from "./routes/TestRoute.tsx";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {SessionContextProvider} from "./contexts/SessionContext.tsx";


const router = createBrowserRouter(
    createRoutesFromElements(
        <Route path="/" element={<App />}>
            <Route path={"/test"} element={<TestRoute></TestRoute>}></Route>
        </Route>
    )
);

const queryClient = new QueryClient()

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <QueryClientProvider client={queryClient}>
          <SessionContextProvider>
            <RouterProvider router={router}></RouterProvider>
          </SessionContextProvider>
      </QueryClientProvider>
  </StrictMode>,
)
