import { useEffect, useState } from 'react';
import LoginForm from './LoginForm';
import { SignupForm } from './SignupForm';
import App from '@/App';
import { Navigate } from 'react-router-dom';
import AuthUtils from '@/lib/AuthUtils';

type Props = {};

function AuthPage({}: Props) {
	const [showLogin, setShowLogin] = useState(true);

	useEffect(() => {
		document.title = 'Influx | Auth';
	});

	return (
		<App>
			{AuthUtils.isLoggedIn() && <Navigate to='/' />}
			{showLogin ? (
				<LoginForm toggleAuthForm={() => setShowLogin((old) => !old)} />
			) : (
				<SignupForm toggleAuthForm={() => setShowLogin((old) => !old)} />
			)}
		</App>
	);
}

export default AuthPage;
