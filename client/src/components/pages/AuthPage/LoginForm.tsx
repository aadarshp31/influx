import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
import { Button } from '@/components/ui/button';
import {
	Form,
	FormControl,
	FormDescription,
	FormField,
	FormItem,
	FormLabel,
	FormMessage
} from '@/components/ui/form';
import { Input } from '@/components/ui/input';
import AuthApi from '@/apis/auth';
import { useNavigate } from 'react-router-dom';
import { AxiosError } from 'axios';
import CONSTANTS from '@/lib/CONSTANTS';
import _ from 'lodash';

const formSchema = z.object({
	username: z.string().min(2, {
		message: 'Username must be at least 2 characters.'
	}),
	password: z.string().min(8, {
		message: 'Password must be at least 8 characters.'
	})
});

type Props = {
	toggleAuthForm: (event: React.MouseEvent<HTMLAnchorElement>) => void;
};

export default function LoginForm({ toggleAuthForm }: Props) {
	const navigate = useNavigate();
	const form = useForm<z.infer<typeof formSchema>>({
		resolver: zodResolver(formSchema),
		defaultValues: {
			username: '',
			password: ''
		}
	});

	async function onSubmit(values: z.infer<typeof formSchema>) {
		try {
			const res = await AuthApi.login(values);
			localStorage.setItem(CONSTANTS.AUTH_TOKEN_KEY, JSON.stringify(res.token));
			localStorage.setItem(CONSTANTS.USER_KEY, JSON.stringify(res.user));
			navigate('/');
		} catch (error: any) {
			if (error && error instanceof AxiosError) {
				const body = error.response?.data;
				const errorMessage = !_.isUndefined(body)
					? error.response?.data.message
					: error.message;

				if (
					_.isUndefined(body?.validationStatus) &&
					!_.isUndefined(error?.response?.status) &&
					error.response.status < 400
				) {
					console.error(errorMessage);
					return;
				}

				if (!body.validationStatus && body?.password) {
					form.setError('password', {
						message: body.password,
						type: 'value'
					});
					form.setFocus('password');
				}

				if (!body.validationStatus && body?.username) {
					form.setError('username', {
						message: body.username,
						type: 'value'
					});
					form.setFocus('username');
				}
				console.error(errorMessage);
				return;
			}
			console.error(error.message);
		}
	}

	return (
		<section className=''>
			<div className='flex items-center justify-center bg-white px-4 py-10 sm:px-6 sm:py-16 lg:px-8'>
				<div className='xl:mx-auto xl:w-full xl:max-w-sm 2xl:max-w-md'>
					<div className='mb-2'>
						<svg
							width='50'
							height='56'
							viewBox='0 0 50 56'
							fill='none'
							xmlns='http://www.w3.org/2000/svg'
						>
							<path
								d='M23.2732 0.2528C20.8078 1.18964 2.12023 12.2346 1.08477 13.3686C0 14.552 0 14.7493 0 27.7665C0 39.6496 0.0986153 41.1289 0.83823 42.0164C2.12023 43.5449 23.2239 55.4774 24.6538 55.5267C25.9358 55.576 46.1027 44.3832 48.2229 42.4602C49.3077 41.474 49.3077 41.3261 49.3077 27.8158C49.3077 14.3055 49.3077 14.1576 48.2229 13.1714C46.6451 11.7415 27.1192 0.450027 25.64 0.104874C24.9497 -0.0923538 23.9142 0.00625992 23.2732 0.2528ZM20.2161 21.8989C20.2161 22.4906 18.9835 23.8219 17.0111 25.3997C15.2361 26.7803 13.8061 27.9637 13.8061 28.0623C13.8061 28.1116 15.2361 29.0978 16.9618 30.2319C18.6876 31.3659 20.2655 32.6479 20.4134 33.0917C20.8078 34.0286 19.871 35.2119 18.8355 35.2119C17.8001 35.2119 9.0233 29.3936 8.67815 28.5061C8.333 27.6186 9.36846 26.5338 14.3485 22.885C17.6521 20.4196 18.4904 20.0252 19.2793 20.4196C19.7724 20.7155 20.2161 21.3565 20.2161 21.8989ZM25.6893 27.6679C23.4211 34.9161 23.0267 35.7543 22.1391 34.8668C21.7447 34.4723 22.1391 32.6479 23.6677 27.9637C26.2317 20.321 26.5275 19.6307 27.2671 20.3703C27.6123 20.7155 27.1685 22.7864 25.6893 27.6679ZM36.0932 23.2302C40.6788 26.2379 41.3198 27.0269 40.3337 28.1609C39.1503 29.5909 31.6555 35.2119 30.9159 35.2119C29.9298 35.2119 28.9436 33.8806 29.2394 33.0424C29.3874 32.6479 30.9652 31.218 32.7403 29.8867L35.9946 27.4706L32.5431 25.1532C30.6201 23.9205 29.0915 22.7371 29.0915 22.5892C29.0915 21.7509 30.2256 20.4196 30.9159 20.4196C31.3597 20.4196 33.6771 21.7016 36.0932 23.2302Z'
								fill='black'
							></path>
						</svg>
					</div>
					<h2 className='text-2xl font-bold leading-tight text-black'>
						Sign in to your account
					</h2>
					<p className='mt-2text-sm text-gray-600 '>
						Don&#x27;t have an account?{' '}
						<a
							href='#'
							title=''
							className='font-semibold text-black transition-all duration-200 hover:underline'
							onClick={toggleAuthForm}
						>
							Create a free account
						</a>
					</p>
					<Form {...form}>
						<form onSubmit={form.handleSubmit(onSubmit)} className='mt-8'>
							<div className='space-y-5'>
								<FormField
									control={form.control}
									name='username'
									render={({ field }) => (
										<FormItem>
											<FormLabel className='text-base font-medium text-gray-900'>
												Username
											</FormLabel>
											<FormControl className='mt-2'>
												<Input
													className='flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 disabled:cursor-not-allowed disabled:opacity-50'
													placeholder=''
													{...field}
													autoComplete='username'
												/>
											</FormControl>
											<FormDescription>
												This is used to login and access your account.
											</FormDescription>
											<FormMessage />
										</FormItem>
									)}
								/>
								<FormField
									control={form.control}
									name='password'
									render={({ field }) => (
										<FormItem>
											<div className='flex items-center justify-between'>
												<FormLabel className='text-base font-medium text-gray-900'>
													Password
												</FormLabel>
												<a
													href='#'
													title=''
													className='text-sm font-semibold text-black hover:underline'
												>
													Forgot password?
												</a>
											</div>
											<FormControl className='mt-2'>
												<Input
													className='flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 disabled:cursor-not-allowed disabled:opacity-50'
													placeholder=''
													{...field}
													type='password'
													autoComplete='new-password'
												/>
											</FormControl>
											<FormDescription>
												Password must be at least 8 characters long.
											</FormDescription>
											<FormMessage />
										</FormItem>
									)}
								/>
								<Button
									className='inline-flex w-full items-center justify-center rounded-md bg-black px-3.5 py-2.5 font-semibold leading-7 text-white hover:bg-black/80'
									type='submit'
								>
									Submit
								</Button>
							</div>
						</form>
					</Form>
				</div>
			</div>
		</section>
	);
}
