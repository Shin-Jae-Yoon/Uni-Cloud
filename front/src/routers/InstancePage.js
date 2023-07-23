import React, { useState, useEffect } from "react";
import { Navbar, Container, Button, Nav, Toast } from "react-bootstrap";
import axios from "axios";
import { useHistory } from "react-router-dom";
import "../scss/InstancePage.scss";
import DashBoardPage from "./InstancePageComponent/DashBoardPage";
import InstanceCreatePage from "./InstancePageComponent/InstanceCreatePage";
import CloudManagePage from "./InstancePageComponent/CloudManagePage";
import PermissionErrorPage from "./InstancePageComponent/PermissionErrorPage";

function InstancePage(props) {
  let history = useHistory();
  const [selectedMenu, setSelectedMenu] = useState("EC2 대시보드");
  const [permission, setPermission] = useState("");
  const [errorHeader, setErrorHeader] = useState(null);

  const onPermissionHandler = () => {
    axios
      .get("http://localhost:8080/api/v1/auth/permission", {
        withCredentials: true,
      })
      .then((res) => {
        if (res.status === 200) {
          setPermission("true");
        }
      })
      .catch((error) => {
        if (
          error.response &&
          error.response.data &&
          error.response.data.code === "403"
        ) {
          setErrorHeader(error.response.data.message);
        } else {
          console.log(error);
        }
      });
  };

  const onLogoutHandler = (event) => {
    event.preventDefault();
    axios
      .get("http://localhost:8080/api/v1/auth/logout", {
        withCredentials: true,
      })
      .then((res) => {
        console.log(res);
        if (res.status === 200) {
          props.setisAuthorized(false);
          alert("로그아웃 성공 !");
          history.push("/");
        } else {
          alert("로그아웃 실패 !");
        }
      })
      .catch((error) => {
        console.log(error);
        alert("로그아웃 중 오류가 발생했습니다.");
      });
  };

  const handleAlertClose = () => {
    setErrorHeader(null);
  };

  const handleMenuClick = (menu) => {
    setSelectedMenu(menu);
  };

  const handleCloudManageClick = () => {
    onPermissionHandler();
    handleMenuClick("클라우드 관리");
  };

  const renderMainContent = () => {
    switch (selectedMenu) {
      case "인스턴스 생성":
        return <InstanceCreatePage></InstanceCreatePage>;
      case "클라우드 관리":
        if (permission === "true") {
          return <CloudManagePage></CloudManagePage>;
        } else {
          return (
            <div>
              <Toast
                show={errorHeader !== null}
                onClose={handleAlertClose}
                delay={2000}
                autohide
                className="toast-warn"
              >
                <Toast.Header className="toast-warn-header">
                  <strong className="me-auto">{errorHeader}</strong>
                </Toast.Header>
                <Toast.Body className="toast-warn-body">
                  <p>관리자만 접근 가능한 페이지 입니다.</p>
                </Toast.Body>
              </Toast>

              <PermissionErrorPage></PermissionErrorPage>
            </div>
          );
        }
      default:
        return <DashBoardPage />;
    }
  };

  return (
    <div className="instancePage">
      <div className="topNavbar">
        <Navbar className="Navbar" variant="dark">
          <Container>
            <Navbar.Brand className="leftbox">Uni Cloud</Navbar.Brand>
            <Navbar.Toggle />
            <Navbar.Collapse className="justify-content-end">
              <div className="rightbox">
                <Navbar.Text>
                  환영합니다.
                  <span className="name"> {props.showID}</span>
                  &nbsp;님
                </Navbar.Text>
                <Button
                  variant="secondary"
                  type="submit"
                  onClick={onLogoutHandler}
                >
                  로그아웃
                </Button>
              </div>
            </Navbar.Collapse>
          </Container>
        </Navbar>
      </div>

      <div className="sideBar">
        <span className="fs-4">EC2 DashBoard</span>
        <hr />
        <Nav className="flex-column">
          <Nav.Link
            href="#"
            className={`nav-link ${
              selectedMenu === "EC2 대시보드" ? "" : "link-dark"
            }`}
            onClick={() => {
              handleMenuClick("EC2 대시보드");
            }}
          >
            EC2 대시보드
          </Nav.Link>
          <Nav.Link
            href="#"
            className={`nav-link ${
              selectedMenu === "인스턴스 생성" ? "" : "link-dark"
            }`}
            onClick={() => handleMenuClick("인스턴스 생성")}
          >
            인스턴스 생성
          </Nav.Link>
        </Nav>

        <div className="blank"></div>

        <span className="fs-4 manager-menu">관리자 전용 메뉴</span>
        <hr />
        <Nav className="flex-column">
          <Nav.Link
            href="#"
            className={`nav-link ${
              selectedMenu === "클라우드 관리" ? "" : "link-dark"
            }`}
            onClick={handleCloudManageClick}
          >
            클라우드 관리
          </Nav.Link>
        </Nav>
      </div>
      <div className="mainContent">{renderMainContent()}</div>
    </div>
  );
}

export default InstancePage;
