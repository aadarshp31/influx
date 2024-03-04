import AuthenticationPage from "@/components/pages/AuthenticationPage";
import HomePage from "@/components/pages/HomePage";
import { createBrowserRouter } from "react-router-dom";

const router = createBrowserRouter([
  {
    path: "/",
    Component: HomePage
  },
  {
    path: "/login",
    Component: AuthenticationPage
  }
]);

export default router;