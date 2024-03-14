import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { RouterProvider } from 'react-router-dom';
import router from './routes/router.ts';
import App from './App.tsx';
import Header from './components/common/Header.tsx';
import Footer from './components/common/Footer.tsx';

ReactDOM.createRoot(document.getElementById('root')!).render(
	<React.StrictMode>
		<App>
			<Header />
			<RouterProvider router={router} />
			<Footer />
		</App>
	</React.StrictMode>
);
