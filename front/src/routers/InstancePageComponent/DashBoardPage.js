import React, { useState, useEffect } from "react";
import {
  Table,
  Dropdown,
  DropdownButton,
  Toast,
  FloatingLabel,
  Form,
  Button,
} from "react-bootstrap";
import axios from "axios";
import "../../scss/InstancePageComponent/DashBoardPage.scss";

function DashBoardPage() {
  const [instanceArray, setInstanceArray] = useState([]);
  const [errorHeader, setErrorHeader] = useState(null);
  const [selectedInstanceId, setSelectedInstanceId] = useState();
  const [selectedCloudId, setSelectedCloudId] = useState();
  const [cloudArray, setCloudArray] = useState([]);

  const onDashBoardHandler = () => {
    axios
      .get("http://localhost:8080/api/v1/instance", {
        withCredentials: true,
      })
      .then((res) => {
        if (res.status === 200) {
          setInstanceArray(res.data);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const onCloudListHandler = () => {
    axios
      .get("http://localhost:8080/api/v1/cloud", {
        withCredentials: true,
      })
      .then((res) => {
        if (res.status === 200) {
          setCloudArray(res.data);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  useEffect(() => {
    onDashBoardHandler();
    onCloudListHandler();
  }, []);

  const getStatusStyle = (status) => {
    switch (status) {
      case "READY":
        return { color: "black" };
      case "RUNNING":
        return { color: "green" };
      case "STOPPED":
        return { color: "orange" };
      case "TERMINATED":
        return { color: "red" };
      default:
        return {};
    }
  };

  const handleStatusChange = (instanceId, status) => {
    const url = `http://localhost:8080/api/v1/instance/${instanceId}/status`;
    const data = { status: status };

    axios
      .put(url, data, {
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then(() => {
        onDashBoardHandler();
      })
      .catch((error) => {
        if (
          error.response &&
          error.response.data &&
          error.response.data.code === "400"
        ) {
          setErrorHeader(error.response.data.message);
        } else {
          console.log(error);
        }
      });
  };

  const handlePutRequest = () => {
    const url = `http://localhost:8080/api/v1/instance/${selectedInstanceId}/cloud`;
    const data = { cloudId: selectedCloudId };
  
    axios
      .put(url, data, {
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then(() => {
        onDashBoardHandler();
        alert("클라우드 스펙 변경이 완료되었습니다.");
      })
      .catch((error) => {
        if (
          error.response &&
          error.response.data &&
          error.response.data.code === "400"
        ) {
          setErrorHeader(error.response.data.message);
        } else {
          console.log(error);
        }
      });
  };

  const handleAlertClose = () => {
    setErrorHeader(null);
  };

  return (
    <div className="dashBoardPage">
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
          <p>
            인스턴스 상태는 아래의 두 가지만 가능합니다. <br />
            <strong>RUNNING(실행 중), STOPPED(일시정지)</strong>
          </p>
        </Toast.Body>
      </Toast>

      <div className="white-box semi-header">보유중인 인스턴스 리소스</div>
      <div className="instanceInfo" style={{ overflow: "auto" }}></div>
      <Table responsive="lg" className="mainTable white-box">
        <thead>
          <tr>
            <th>ID</th>
            <th>서버명</th>
            <th>운영체제</th>
            <th>RAM</th>
            <th>SSD</th>
            <th>일별요금</th>
            <th>현재상태</th>
            <th></th>
          </tr>
        </thead>
        <tbody
          style={{
            textAlign: "center",
            verticalAlign: "middle",
          }}
        >
          {instanceArray.length > 0 ? (
            instanceArray.map((instance) => (
              <tr key={instance.id}>
                <td>{instance.id}</td>
                <td>{instance.cloud.serverName}</td>
                <td>{instance.cloud.component.operatingSystem}</td>
                <td>{instance.cloud.component.ram}</td>
                <td>{instance.cloud.component.ssd}</td>
                <td>{instance.cloud.dailyFee.fee}</td>
                <td style={getStatusStyle(instance.status)}>
                  {instance.status}
                </td>
                <td>
                  <DropdownButton
                    title="상태 변경"
                    variant="secondary"
                    id={`dropdown-status-${instance.id}`}
                  >
                    <Dropdown.Item
                      onClick={() => handleStatusChange(instance.id, "READY")}
                    >
                      READY
                    </Dropdown.Item>
                    <Dropdown.Item
                      onClick={() => handleStatusChange(instance.id, "RUNNING")}
                    >
                      RUNNING
                    </Dropdown.Item>
                    <Dropdown.Item
                      onClick={() => handleStatusChange(instance.id, "STOPPED")}
                    >
                      STOPPED
                    </Dropdown.Item>
                    <Dropdown.Item
                      onClick={() =>
                        handleStatusChange(instance.id, "TERMINATED")
                      }
                    >
                      TERMINATED
                    </Dropdown.Item>
                  </DropdownButton>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="8">데이터가 없습니다.</td>
            </tr>
          )}
        </tbody>
      </Table>

      <div className="blank"></div>

      <div className="white-box semi-header">클라우드 스펙 변경</div>
      <div className="instanceInfo" style={{ overflow: "auto" }}></div>
      <div className="white-box">
        <FloatingLabel
          controlId="floatingSelectInstanceId"
          label="클릭하여 인스턴스를 선택하세요"
        >
          <Form.Select
            aria-label="Floating label select example"
            onChange={(e) => setSelectedInstanceId(e.target.value)}
          >
            <option>변경할 인스턴스를 선택하세요</option>
            {instanceArray.map((instance) => (
              <option key={instance.id} value={instance.id}>
                ID: {instance.id}, 서버명: {instance.cloud.serverName}, 상태:{" "}
                {instance.status}
              </option>
            ))}
          </Form.Select>
        </FloatingLabel>
        <div className="blank"></div>
        <FloatingLabel
          controlId="floatingSelectCloudId"
          label="클릭하여 스펙을 선택하세요"
        >
          <Form.Select
            aria-label="Floating label select example"
            onChange={(e) => setSelectedCloudId(e.target.value)}
          >
            <option>변경할 스펙을 선택하세요</option>
            {cloudArray.map((cloud) => (
              <option key={cloud.cloudId} value={cloud.cloudId}>
                서버명: {cloud.serverName}, 운영체제:{" "}
                {cloud.component.operatingSystem}, RAM: {cloud.component.ram},
                SSD: {cloud.component.ssd}, 일별요금: {cloud.dailyFee.fee}
              </option>
            ))}
          </Form.Select>
        </FloatingLabel>

        <div className="form-btn">
        <Button onClick={handlePutRequest} variant="secondary" className="mt-4">변경</Button>
        </div>
      </div>
    </div>
  );
}

export default DashBoardPage;
