import LoginPage from "@/components/pages/LoginPage/LoginPage";
import HomePage from "@/components/pages/HomePage/HomePage";
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