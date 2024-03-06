import LoginPage from "@/components/pages/LoginPage";
import HomePage from "@/components/pages/HomePage";
import { createBrowserRouter } from "react-router-dom";

const router = createBrowserRouter([
  {
    path: "/",
    Component: HomePage
  },
  {
    path: "/login",
    Component: LoginPage
  }
]);

export default router;