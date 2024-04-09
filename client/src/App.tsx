import './App.css';
import Footer from './components/common/Footer';
import Header from './components/common/Header';

function App({ children }: any) {
	return (
		<div>
			<Header />
			{children}
			<Footer />
		</div>
	);
}

export default App;
