import AuthorPage from '@/components/pages/AuthorPage/AuthorPage';
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
	}
]);

export default router;
