import React, { useState, useEffect } from "react";
import { Table, Form, Button } from "react-bootstrap";
import axios from "axios";
import "../../scss/InstancePageComponent/CloudManagePage.scss";

function CloudManagePage(props) {
  const [cloudArray, setCloudArray] = useState([]);
  const [componentData, setComponentData] = useState({
    operatingSystems: [],
    rams: [],
    ssds: [],
  });
  const [selectedServerName, setSelectedServerName] = useState("");
  const [selectedOperatingSystem, setSelectedOperatingSystem] = useState("");
  const [selectedRam, setSelectedRam] = useState("");
  const [selectedSsd, setSelectedSsd] = useState("");

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

  const onComponentListHandler = () => {
    axios
      .get("http://localhost:8080/api/v1/cloud/component", {
        withCredentials: true,
      })
      .then((res) => {
        if (res.status === 200) {
          setComponentData(res.data);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };


  const handleCreateCloud = () => {
    const requestBody = {
      serverName: selectedServerName,
      componentRequest: {
        operatingSystem: selectedOperatingSystem,
        ram: selectedRam,
        ssd: selectedSsd,
      },
    };

    axios
      .post("http://localhost:8080/api/v1/cloud", requestBody, {
        withCredentials: true,
      })
      .then((res) => {
        alert("새로운 클라우드 생성에 성공하였습니다.");
        onCloudListHandler();
      })
      .catch((error) => {
        alert("새로운 클라우드 생성에 실패하였습니다.");
      });
  };

  useEffect(() => {
    onCloudListHandler();
    onComponentListHandler();
  }, []);
  return (
    <div className="cloudManagePage">
      <div className="white-box semi-header">가동중인 클라우드 서버</div>
      <div className="cloudinfo" style={{ overflow: "auto" }}></div>
      <Table responsive="lg" className="mainTable white-box">
        <thead>
          <tr>
            <th>ID</th>
            <th>서버명</th>
            <th>운영체제</th>
            <th>RAM</th>
            <th>SSD</th>
            <th>일별요금</th>
          </tr>
        </thead>
        <tbody
          style={{
            textAlign: "center",
            verticalAlign: "middle",
          }}
        >
          {cloudArray.length > 0 ? (
            cloudArray.map((cloud) => (
              <tr key={cloud.cloudId}>
                <td>{cloud.cloudId}</td>
                <td>{cloud.serverName}</td>
                <td>{cloud.component.operatingSystem}</td>
                <td>{cloud.component.ram}</td>
                <td>{cloud.component.ssd}</td>
                <td>{cloud.dailyFee.fee}</td>
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

      <div className="white-box semi-header">새로운 클라우드 생성</div>
      <div className="cloudinfo" style={{ overflow: "auto" }}></div>
      <Table responsive="lg" className="mainTable white-box">
        <thead>
          <tr>
            <th>서버명</th>
            <th>운영체제</th>
            <th>RAM</th>
            <th>SSD</th>
          </tr>
        </thead>
        <tbody>
          <tr>
          <td>
              <Form.Control
                type="text"
                placeholder="서버명을 입력하세요"
                value={selectedServerName}
                onChange={(e) => setSelectedServerName(e.target.value)}
              />
            </td>
            <td>
              <Form.Select
                value={selectedOperatingSystem}
                onChange={(e) => setSelectedOperatingSystem(e.target.value)}
              >
                <option value="">운영체제를 선택하세요</option>
                {componentData.operatingSystems.map((os) => (
                  <option key={os} value={os}>
                    {os}
                  </option>
                ))}
              </Form.Select>
            </td>
            <td>
              <Form.Select
                value={selectedRam}
                onChange={(e) => setSelectedRam(e.target.value)}
              >
                <option value="">RAM을 선택하세요</option>
                {componentData.rams.map((ram) => (
                  <option key={ram} value={ram}>
                    {ram}
                  </option>
                ))}
              </Form.Select>
            </td>
            <td>
              <Form.Select
                value={selectedSsd}
                onChange={(e) => setSelectedSsd(e.target.value)}
              >
                <option value="">SSD를 선택하세요</option>
                {componentData.ssds.map((ssd) => (
                  <option key={ssd} value={ssd}>
                    {ssd}
                  </option>
                ))}
              </Form.Select>
            </td>
          </tr>
        </tbody>
      </Table>
      <div className="form-btn">
          <Button
            onClick={handleCreateCloud}
            variant="secondary"
            className="mt-4"
          >
            생성
          </Button>
        </div>

      <div className="blank"></div>

      <div className="white-box semi-header">스펙 기준표</div>

      <div className="server-spec white-box">
        <div className="item1">
          <h3>운영체제</h3>
          <table>
            <tr>
              <th>타입</th>
            </tr>
            <tr>
              <td>windows</td>
            </tr>
            <tr>
              <td>macOS</td>
            </tr>
            <tr>
              <td>ubuntu</td>
            </tr>
            <tr>
              <td>debian</td>
            </tr>
          </table>
        </div>

        <div className="item2">
          <h3>RAM</h3>
          <table>
            <tr>
              <th>타입</th>
              <th>용량</th>
              <th>일별요금</th>
            </tr>
            <tr>
              <td>micro</td>
              <td>1GB</td>
              <td>0원</td>
            </tr>
            <tr>
              <td>small</td>
              <td>2GB</td>
              <td>500원</td>
            </tr>
            <tr>
              <td>medium</td>
              <td>4GB</td>
              <td>1000원</td>
            </tr>
            <tr>
              <td>large</td>
              <td>8GB</td>
              <td>2000원</td>
            </tr>
            <tr>
              <td>x_large</td>
              <td>16GB</td>
              <td>4000원</td>
            </tr>
            <tr>
              <td>x2_large</td>
              <td>32GB</td>
              <td>8000원</td>
            </tr>
            <tr>
              <td>x3_large</td>
              <td>64GB</td>
              <td>16000원</td>
            </tr>
          </table>
        </div>

        <div className="item2">
          <h3>SSD</h3>
          <table>
            <tr>
              <th>타입</th>
              <th>용량</th>
              <th>일별요금</th>
            </tr>
            <tr>
              <td>micro</td>
              <td>64GB</td>
              <td>0원</td>
            </tr>
            <tr>
              <td>small</td>
              <td>128GB</td>
              <td>200원</td>
            </tr>
            <tr>
              <td>medium</td>
              <td>256GB</td>
              <td>400원</td>
            </tr>
            <tr>
              <td>large</td>
              <td>512GB</td>
              <td>800원</td>
            </tr>
            <tr>
              <td>x_large</td>
              <td>1TB</td>
              <td>1600원</td>
            </tr>
            <tr>
              <td>x2_large</td>
              <td>2TB</td>
              <td>3200원</td>
            </tr>
            <tr>
              <td>x3_large</td>
              <td>3TB</td>
              <td>6400원</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  );
}

export default CloudManagePage;
