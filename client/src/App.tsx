import './App.css';
import Footer from './components/common/Footer';
import Header from './components/common/Header';

function App({ children }: any) {
	return (
		<div>
			<Header />
			<div style={{ minHeight: '800px' }}>{children}</div>
			<Footer />
		</div>
	);
}

export default App;
