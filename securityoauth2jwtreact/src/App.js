import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import JwtCallback from './JwtCallback';
import Content from './Content';

function App() {
	return (
    <div className='w-full'>
		<Router>
			<Routes>
				<Route path="/" element={<Content />} />
				<Route path="/oauth2/callback" element={<JwtCallback />} />
			</Routes>
		</Router>
    
    </div>
	);
}
export default App;