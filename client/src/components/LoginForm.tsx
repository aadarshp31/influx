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

const formSchema = z.object({
	username: z.string().min(2, {
		message: 'Username must be at least 2 characters.'
	}),
	password: z.string().min(8, {
		message: 'Password must be at least 8 characters.'
	})
});

export default function LoginForm() {
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
			localStorage.setItem('token', JSON.stringify(res.token));
			localStorage.setItem('user', JSON.stringify(res.user));
			console.log('Successfully logged in!', values, res);
		} catch (error: any) {
			console.error(error);
		}
	}

	return (
		<section
			className='flex flex-col gap-4 p-4 border-2 rounded-lg shadow-md'
			style={{ minWidth: '200px' }}
		>
			<h3 className='text-2xl'>Login Form</h3>
			<Form {...form}>
				<form onSubmit={form.handleSubmit(onSubmit)} className='space-y-8'>
					<FormField
						control={form.control}
						name='username'
						render={({ field }) => (
							<FormItem>
								<FormLabel>Username</FormLabel>
								<FormControl>
									<Input placeholder='' {...field} />
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
								<FormLabel>Password</FormLabel>
								<FormControl>
									<Input placeholder='' {...field} />
								</FormControl>
								<FormDescription>
									Password must be at least 8 characters long.
								</FormDescription>
								<FormMessage />
							</FormItem>
						)}
					/>
					<Button type='submit'>Submit</Button>
				</form>
			</Form>
		</section>
	);
}
