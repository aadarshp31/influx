import HeroSection from './layouts/HeroSection';
import FeaturesSection from './layouts/FeaturesSection';
import FAQSection from './layouts/FAQSection';
import PricingSection from './layouts/PricingSection';
import NewsLetterSection from './layouts/NewsLetterSection';
import App from '@/App';
import { useEffect } from 'react';

type Props = {};

export function HomePage({}: Props) {
	useEffect(() => {
		document.title = 'Influx | Home';
	});

	return (
		<App>
			<div className='w-full'>
				{/* Hero Section */}
				<HeroSection />
				{/* Features Section */}
				<FeaturesSection />
				{/* FAQs */}
				<FAQSection />
				{/* Pricing Section */}
				<PricingSection />
				{/* NewsLetter */}
				<NewsLetterSection />
			</div>
		</App>
	);
}

export default HomePage;
