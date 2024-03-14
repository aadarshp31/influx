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
	firstname: z.string().min(2, {
		message: 'Firstname must be at least 2 characters.'
	}),
	lastname: z.string().min(2, {
		message: 'Lastname must be at least 2 characters.'
	}),
	email: z.string().email({
		message: 'Please enter a valid email.'
	}),
	username: z.string().min(2, {
		message: 'Username must be at least 2 characters.'
	}),
	password: z.string().min(8, {
		message: 'Password must be at least 8 characters.'
	})
});

export function SignupForm() {
	const form = useForm<z.infer<typeof formSchema>>({
		resolver: zodResolver(formSchema),
		defaultValues: {
			firstname: '',
			lastname: '',
			email: '',
			username: '',
			password: ''
		}
	});

	async function onSubmit(values: z.infer<typeof formSchema>) {
		try {
			const res = await AuthApi.signup(values);
			console.log('Signed up!', res);
		} catch (error: any) {
			console.error(error);
		}
	}

	return (
		<section className='flex flex-col gap-4 p-2 border-2 rounded-lg shadow-md'>
			<h3 className='text-2xl'>Signup Form</h3>
			<Form {...form}>
				<form onSubmit={form.handleSubmit(onSubmit)} className='space-y-8'>
					<FormField
						control={form.control}
						name='firstname'
						render={({ field }) => (
							<FormItem>
								<FormLabel>First Name</FormLabel>
								<FormControl>
									<Input placeholder='' {...field} />
								</FormControl>
								<FormMessage />
							</FormItem>
						)}
					/>
					<FormField
						control={form.control}
						name='lastname'
						render={({ field }) => (
							<FormItem>
								<FormLabel>Last Name</FormLabel>
								<FormControl>
									<Input placeholder='' {...field} />
								</FormControl>
								<FormMessage />
							</FormItem>
						)}
					/>
					<FormField
						control={form.control}
						name='email'
						render={({ field }) => (
							<FormItem>
								<FormLabel>Email</FormLabel>
								<FormControl>
									<Input placeholder='' {...field} />
								</FormControl>
								<FormMessage />
							</FormItem>
						)}
					/>
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
