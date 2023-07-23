import '../scss/LoginPage.scss';
import React, {useState} from 'react';
import earthimg from '../img/earth.png';
import {Button} from 'react-bootstrap';
import axios from 'axios';
import RegisterPage from './RegisterPage';
import {useHistory} from 'react-router-dom';

function LoginPage(props) {
    const history = useHistory();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const onEmailHandler = (event) => {
        setEmail(event.currentTarget.value);
    };

    const onPasswordHandler = (event) => {
        setPassword(event.currentTarget.value);
    };

    const onLoginHandler = (event) => {
        event.preventDefault();
        axios
            .post('http://localhost:8080/api/v1/auth/login', {
                email: email,
                password: password,
            }, {withCredentials: true})
            .then((res) => {
                if (res.status === 200) {
                    props.setisAuthorized(true);
                    alert('로그인 성공 !');
                    props.setshowID(email);
                    history.push('/instance');
                }
            })
            .catch((error) => {
                if (error.response && error.response.status === 400) {
                    alert('아이디/비밀번호가 틀렸습니다.');
                    const { validation } = error.response.data;
                    const errorMessages = Object.values(validation).join('\n');
                    setErrorMessage(errorMessages);
                } else {
                    console.log('Error:', error);
                    alert('로그인 중 오류가 발생했습니다.');
                }
            });
    };

    return (
        <div className="App">
            <div className="Jumbotron">
                <form onSubmit={onLoginHandler}>
                    <p className="title">Uni Cloud</p>
                    <img src={earthimg} alt="" width={400}/>
                    <label>
                        <p>아이디</p>
                    </label>
                    <input
                        type="text"
                        placeholder="아이디를 입력하세요."
                        value={email}
                        onChange={onEmailHandler}
                    />
                    <label>
                        <p>비밀번호</p>
                    </label>
                    <input
                        type="password"
                        placeholder="비밀번호를 입력하세요."
                        value={password}
                        onChange={onPasswordHandler}
                    />
                    <br />

                    <RegisterPage></RegisterPage>

                    <Button
                        variant="outline-info"
                        type="submit"
                        onClick={onLoginHandler}>
                        접속하기
                    </Button>
                </form>
            </div>
        </div>
    );
}

export default LoginPage;