import App from '@/App';
import { useEffect } from 'react';
import { Link } from 'react-router-dom';

type Props = {};

const authors = [
	{
		name: 'Adarsh Pandey',
		image:
			'https://media.licdn.com/dms/image/D4D03AQGwdVAebLDLDA/profile-displayphoto-shrink_800_800/0/1694711604292?e=1717027200&v=beta&t=MoPFFSE4t1DW_X6RI8oEpeFk7sDy9QXISi4EoJYlhhc',
		position: 'Full Stack Developer',
		linkedInProfile: 'https://www.linkedin.com/in/aadarshp31',
		portfolioWebsite: 'https://adarshpandey.in'
	},
	{
		name: 'Aniket Soni',
		image:
			'https://media.licdn.com/dms/image/D5603AQFU7Rtk1OS0YA/profile-displayphoto-shrink_800_800/0/1695602064209?e=1717027200&v=beta&t=Vnp3x8YwsK-bgQaXA3FbH_zJ8khq-RxMyRNI65VTFdM',
		position: 'Full Stack Developer',
		linkedInProfile: 'https://www.linkedin.com/in/aniketsonii',
		portfolioWebsite: 'https://aniketsonii.github.io'
	}
];

function AuthorPage({}: Props) {
	useEffect(() => {
		document.title = 'Influx | Authors';
	});

	return (
		<App>
			<div className='mx-auto max-w-7xl px-4'>
				{/* greetings */}
				<div className='mt-16 flex items-center'>
					<div className='space-y-6 md:w-3/4'>
						<div className='max-w-max rounded-full border bg-gray-50 p-1 px-3'>
							<p className='text-xs font-semibold leading-normal md:text-sm'>
								Join Us &rarr;
							</p>
						</div>
						<p className='text-3xl font-bold text-gray-900 md:text-4xl'>
							Meet our team
						</p>
						<p className='max-w-4xl text-base text-gray-700 md:text-xl'>
							Our philosophy is simple — build a team of diverse, passionate
							people to build something they want to learn about.
						</p>
					</div>
				</div>
				<hr className='mt-20' />
				{/* TEAM */}
				<div className='mx-auto max-w-7xl px-2 md:px-0'>
					<div className='my-4'>
						<h1 className='text-3xl font-bold'>Our Team</h1>
						<p className='mt-2 text-gray-500'>
							Our team consists of enthusiastic and curious people.
						</p>
					</div>
					<div className='grid grid-cols-1 gap-[30px] md:grid-cols-3'>
						{authors.map((user, index) => (
							<div key={index} className='flex flex-col items-start text-start'>
								<div
									className='relative flex h-[342px] w-full flex-col justify-end rounded-[10px] bg-red-300'
									style={{
										backgroundPosition: 'center',
										backgroundSize: 'cover',
										backgroundRepeat: 'no-repeat'
									}}
								>
									<img
										src={user.image}
										alt={`${user.name}'s profile picture`}
										className='z-0 h-full w-full rounded-[10px] object-cover'
									/>
									<div className='absolute bottom-4 left-4'>
										<h1 className='text-xl font-semibold text-white'>
											{user.name}
										</h1>
										<h6 className='text-base text-white'>{user.position}</h6>
									</div>
								</div>
								<div className='mt-4 flex flex-wrap'>
									<div className='w-auto p-1.5'>
										<Link to={user.portfolioWebsite} target='_blank'>
											<div className='flex h-8 w-8 items-center justify-center rounded-full border border-gray-300 hover:border-gray-400'>
												<svg
													xmlns='http://www.w3.org/2000/svg'
													width='24'
													height='24'
													viewBox='0 0 24 24'
													id='http'
												>
													<path
														fill='none'
														d='M24 24H0V0h24v24z'
														opacity='.87'
													></path>
													<path d='M4.5 11h-2V9H1v6h1.5v-2.5h2V15H6V9H4.5v2zm2.5-.5h1.5V15H10v-4.5h1.5V9H7v1.5zm5.5 0H14V15h1.5v-4.5H17V9h-4.5v1.5zm9-1.5H18v6h1.5v-2h2c.8 0 1.5-.7 1.5-1.5v-1c0-.8-.7-1.5-1.5-1.5zm0 2.5h-2v-1h2v1z'></path>
												</svg>
											</div>
										</Link>
									</div>
									<div className='w-auto p-1.5'>
										<Link to={user.linkedInProfile} target='_blank'>
											<div className='flex h-8 w-8 items-center justify-center rounded-full border border-gray-300 hover:border-gray-400'>
												<svg
													xmlns='http://www.w3.org/2000/svg'
													x='0px'
													y='0px'
													width='50'
													height='50'
													viewBox='0 0 50 50'
												>
													<path d='M41,4H9C6.24,4,4,6.24,4,9v32c0,2.76,2.24,5,5,5h32c2.76,0,5-2.24,5-5V9C46,6.24,43.76,4,41,4z M17,20v19h-6V20H17z M11,14.47c0-1.4,1.2-2.47,3-2.47s2.93,1.07,3,2.47c0,1.4-1.12,2.53-3,2.53C12.2,17,11,15.87,11,14.47z M39,39h-6c0,0,0-9.26,0-10 c0-2-1-4-3.5-4.04h-0.08C27,24.96,26,27.02,26,29c0,0.91,0,10,0,10h-6V20h6v2.56c0,0,1.93-2.56,5.81-2.56 c3.97,0,7.19,2.73,7.19,8.26V39z'></path>
												</svg>
											</div>
										</Link>
									</div>
								</div>
							</div>
						))}
					</div>
				</div>
				{/* Hiring Banner */}
				<div className='flex flex-col items-center gap-x-4 gap-y-4 py-16 md:flex-row'>
					<div className='space-y-6'>
						<p className='text-sm font-semibold md:text-base'>
							Join our team &rarr;
						</p>
						<p className='text-3xl font-bold md:text-4xl'>
							We&apos;re just getting started
						</p>
						<p className='text-base text-gray-600 md:text-lg'>
							If you are intrested in contributing to our hobby projects to
							learn software development, please reach out to any of the authors
							with your ideas.
						</p>
						<button
							type='button'
							className='rounded-md bg-black px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-black/80 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black'
						>
							Join Now
						</button>
					</div>
					<div className='md:mt-o mt-10 w-full'>
						<img
							src='https://images.unsplash.com/photo-1605165566807-508fb529cf3e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2340&q=80'
							alt='Getting Started'
							className='rounded-lg'
						/>
					</div>
				</div>
			</div>
		</App>
	);
}

export default AuthorPage;
