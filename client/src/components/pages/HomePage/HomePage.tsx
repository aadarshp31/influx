import HeroSection from './layouts/HeroSection';
import FeaturesSection from './layouts/FeaturesSection';
import FAQSection from './layouts/FAQSection';
import PricingSection from './layouts/PricingSection';
import NewsLetterSection from './layouts/NewsLetterSection';

type Props = {};

export function HomePage({}: Props) {
	return (
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
	);
}

export default HomePage;
