import React, {useState} from 'react';
import {Button, Modal, Form} from 'react-bootstrap';
import axios from 'axios';

function RegisterPage() {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const onEmailHandler = (event) => {
        setEmail(event.currentTarget.value);
    };

    const onPasswordHandler = (event) => {
        setPassword(event.currentTarget.value);
    };

    const onConfirmPasswordHandler = (event) => {
        setConfirmPassword(event.currentTarget.value);
    };

    const onNameHandler = (event) => {
        setName(event.currentTarget.value);
    };

    function submitAlert() {
        axios
            .post('http://localhost:8080/api/v1/auth/signup', {
                name: name,
                email: email,
                password: password,
            })
            .then((res) => {
                if (res.status === 200) {
                    alert(
                        '축하합니다. 회원가입이 완료되었습니다.'
                    );
                }
            })
            .catch((error) => {
                if (error.response && error.response.status === 400) {
                    const { validation } = error.response.data;
                    const errorMessages = Object.values(validation).join('\n');
                    alert(errorMessages);
                    setErrorMessage(errorMessages);
                } else if (error.response && error.response.status === 409) {
                    alert(error.response.data.message);
                } else {
                    alert('회원가입 중 오류가 발생했습니다.');
                }
            });
    }

    const onSubmitpage = (event) => {
        event.preventDefault();

        if (password !== confirmPassword) {
            return alert(
                '비밀번호와 비밀번호 확인이 다릅니다. 다시 시도해주세요.'
            );
        } else {
            return submitAlert();
        }
    };

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    return (
        <div className="registerModal">
            <Button
                variant="outline-info"
                onClick={handleShow}
                style={{
                    width: '100%',
                }}>
                회원가입
            </Button>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title style={{color: 'rgb(46, 132, 167)'}}>
                        회원가입 시스템
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group
                            className="mb-3"
                            controlId="exampleForm.ControlInput1">
                            <Form.Label style={{color: 'rgb(46, 132, 167)'}}>
                                이름
                            </Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="이름을 입력하세요."
                                value={name}
                                onChange={onNameHandler}
                                autoFocus
                            />
                        </Form.Group>
                        <Form.Group
                            className="mb-3"
                            controlId="exampleForm.ControlInput1">
                            <Form.Label style={{color: 'rgb(46, 132, 167)'}}>
                                아이디
                            </Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="원하는 아이디를 입력하세요."
                                value={email}
                                onChange={onEmailHandler}
                                autoFocus
                            />
                        </Form.Group>
                        <Form.Group
                            className="mb-3"
                            controlId="exampleForm.ControlInput2">
                            <Form.Label style={{color: 'rgb(46, 132, 167)'}}>
                                비밀번호
                            </Form.Label>
                            <Form.Control
                                type="password"
                                placeholder="비밀번호를 입력하세요."
                                value={password}
                                onChange={onPasswordHandler}
                                autoFocus
                            />
                        </Form.Group>
                        <Form.Group
                            className="mb-3"
                            controlId="exampleForm.ControlInput2">
                            <Form.Label style={{color: 'rgb(46, 132, 167)'}}>
                                비밀번호 확인
                            </Form.Label>
                            <Form.Control
                                type="password"
                                placeholder="비밀번호를 한번 더 입력하세요."
                                value={confirmPassword}
                                onChange={onConfirmPasswordHandler}
                                autoFocus
                            />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        닫기
                    </Button>
                    <Button
                        style={{color: 'rgb(46, 132, 167)'}}
                        variant="outline-info"
                        onClick={onSubmitpage}
                        type="submit">
                        가입하기
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
}

export default RegisterPage;