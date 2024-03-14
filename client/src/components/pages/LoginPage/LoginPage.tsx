import LoginForm from '../../LoginForm';

type Props = {};

const LoginPage = (_props: Props) => {
	return (
		<>
			<h1 className='text-xl text-center'>Login Page</h1>
			<div className='container w-8/12 mx-auto'>
				<LoginForm />
			</div>
		</>
	);
};

export default LoginPage;
