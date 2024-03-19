import HomePage from '@/components/pages/HomePage/HomePage';
import { createBrowserRouter } from 'react-router-dom';

const router = createBrowserRouter([
	{
		path: '/',
		Component: HomePage,
	}
]);

export default router;
