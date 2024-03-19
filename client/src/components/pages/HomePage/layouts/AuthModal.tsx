import {
	AlertDialog,
	AlertDialogCancel,
	AlertDialogContent,
	AlertDialogHeader,
	AlertDialogTrigger
} from '@/components/ui/alert-dialog';
import { Button } from '@/components/ui/button';
import { SignupForm } from '@/components/SignupForm';
import { useState } from 'react';
import LoginForm from '@/components/LoginForm';

type Props = {};

function AuthModal({}: Props) {
	const [isSignUp, setIsSignUp] = useState(false);

	const toggleAuthForm = () => {
		setIsSignUp(!isSignUp);
	};

	return (
		<>
			<AlertDialog>
				<div className='hidden lg:block'>
					<AlertDialogTrigger asChild>
						<Button
							variant='outline'
							className='rounded-md bg-black px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-black/80 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black'
						>
							Login
						</Button>
					</AlertDialogTrigger>
				</div>
				<div className='lg:hidden'>
					<AlertDialogTrigger asChild>
						<Button
							variant='outline'
							className='mt-4 w-full rounded-md bg-black px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-black/80 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black'
						>
							Login
						</Button>
					</AlertDialogTrigger>
				</div>
				<AlertDialogContent className=' dark:bg-black'>
					<AlertDialogHeader>
						<AlertDialogCancel className='right-6 absolute'>
							X
						</AlertDialogCancel>
						{isSignUp ? (
							<div className='fadeIn 1s ease-in-out'>
								<SignupForm toggleAuthForm={toggleAuthForm} />
							</div>
						) : (
							<LoginForm toggleAuthForm={toggleAuthForm} />
						)}
					</AlertDialogHeader>
				</AlertDialogContent>
			</AlertDialog>
		</>
	);
}

export default AuthModal;
