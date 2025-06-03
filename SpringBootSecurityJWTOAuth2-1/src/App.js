import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import JwtCallback from 'JwtCallback';

function App() {
	return (
		<Router>
			<Routes>
				<Route path="/" element={<h1>홈</h1>} />
				<Route path="/oauth2/callback" element={<JwtCallback />} />
			</Routes>
		</Router>
	);
}
export default App;