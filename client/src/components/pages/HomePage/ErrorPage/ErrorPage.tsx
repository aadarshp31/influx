import App from '@/App';
import { Button } from '@/components/ui/button';
import { useEffect } from 'react';
import { Link } from 'react-router-dom';

type Props = {};

function ErrorPage({}: Props) {
	useEffect(() => {
		document.title = 'Influx | 404';
	});

	return (
		<App>
			<section className='p-10 max-w-full'>
				<div className='container'>
					<div className='row'>
						<div className='col-sm-12'>
							<div
								className='col-sm-10 col-sm-offset-1 text-center'
								style={{ minHeight: '40rem' }}
							>
								<div className='four_zero_four_bg min-h-96'>
									<h1 className='text-center text-8xl'>404</h1>
								</div>

								<div className='contant_box_404'>
									<h3 className='text-3xl'>Look like you're lost</h3>
									<p>the page you are looking for not avaible!</p>
									<Link to='/'>
										<Button className='m-4'>Go to Home</Button>
									</Link>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</App>
	);
}

export default ErrorPage;
