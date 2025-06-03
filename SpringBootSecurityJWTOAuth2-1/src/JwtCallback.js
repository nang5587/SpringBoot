import React from 'react';
import axios from 'axios';
import {useNavigate} from 'react-router-dom';

const JwtCallback = () => {
	const navigate = useNavigate();
	
	useEffect(()=> {
		const fetchJWT = async () => {
			try{
				const response = await axios.get('http://localhost:8080/api/jwtcallback', {
					withCredentials: true
				});
				const jwtToken = response.headers['authorization'];
				if(jwtToken){
					localStorage.setItem('jwtToken', jwtToken);
					navigate('/');
				}
				else{
					console.log("헤더 없음");
				}
			}
			catch(error){
				console.error("콜백 처리 중 에러: " + error)
			}
		};
		fetchJWT();
	}, [navigate]);
	return <div>로그인 처리 중입니다...</div>
};
export default JwtCallback;