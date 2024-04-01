type Props = {};

function HeroSection({}: Props) {
	return (
		<div className='relative w-full bg-white'>
			<div className='mx-auto max-w-7xl lg:px-8'>
				<div className='flex flex-col justify-center px-4 py-10 lg:px-6'>
					<div className='mt-10 flex max-w-max items-center space-x-2 rounded-full border p-2'>
						<p className='text-xs font-medium md:text-sm'>
							Lorem ipsum dolor sit amet consectetur.
							<span className='ml-2 cursor-pointer font-bold'>
								Read More &rarr;
							</span>
						</p>
					</div>
					<h1 className='mt-8 max-w-4xl text-3xl font-bold tracking-tight text-black md:text-4xl lg:text-6xl'>
						Connect with people who really cares about your work
					</h1>
					<p className='mt-8 max-w-3xl text-lg text-gray-700'>
						Influx is a meeting app that allows you to host virtual meetings or
						one-on-one session if that's what you prefer.
					</p>
					<div className='mt-8'>
						<button
							type='button'
							className='rounded-md bg-black px-3 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-black/80 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black'
						>
							Subscribe
						</button>
					</div>
				</div>
				<div className='rounded-lg bg-gray-200 p-4'>
					<img
						className='aspect-[3/2] w-full rounded-lg bg-gray-50 object-cover lg:aspect-auto lg:h-[500px] lg:object-center'
						src='https://images.unsplash.com/photo-1522202176988-66273c2fd55f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjF8fHBlb3BsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=800&q=60'
						alt=''
					/>
				</div>
			</div>
		</div>
	);
}

export default HeroSection;
