import AuthorPage from '@/components/pages/AuthorPage/AuthorPage';
import ErrorPage from '@/components/pages/HomePage/ErrorPage/ErrorPage';
import HomePage from '@/components/pages/HomePage/HomePage';
import { createBrowserRouter } from 'react-router-dom';

const router = createBrowserRouter([
	{
		path: '/',
		Component: HomePage,
	},
	{
		path: '/authors',
		Component: AuthorPage
	},
	{
		path: '/about',
		Component: AuthorPage
	},
	{
		path: '*',
		Component: ErrorPage
	}
]);

export default router;
