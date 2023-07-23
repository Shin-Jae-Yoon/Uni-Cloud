import React, { useState, useEffect } from "react";
import {
  FloatingLabel,
  Form,
  Button,
} from "react-bootstrap";
import axios from "axios";
import "../../scss/InstancePageComponent/InstanceCreatePage.scss";

function InstanceCreatePage(props) {
  const [cloudArray, setCloudArray] = useState([]);
  const [selectedCloudId, setSelectedCloudId] = useState();
  const [days, setDays] = useState(0);
  const [estimatedFee, setEstimatedFee] = useState(0);

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

  useEffect(
    () => {
      onCloudListHandler();
      calculateEstimatedFee();
    },
    [],
    [selectedCloudId, days, cloudArray]
  );

  const handlePostRequest = () => {
    if (!selectedCloudId) {
      return;
    }

    const url = "http://localhost:8080/api/v1/instance";
    const data = { cloudId: selectedCloudId };

    axios
      .post(url, data, {
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then(() => {
        setSelectedCloudId("");
        alert("새로운 인스턴스 생성에 성공하였습니다.")
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const calculateEstimatedFee = () => {
    if (!selectedCloudId || days === 0 || cloudArray.length === 0) {
      setEstimatedFee(0);
      return;
    }

    const selectedCloud = cloudArray.find(
      (cloud) => cloud.cloudId == selectedCloudId
    );

    const fee = selectedCloud ? selectedCloud.dailyFee.fee * days : 0;

    setEstimatedFee(fee);
  };

  const formatCurrency = (value) => {
    return value.toLocaleString();
  };
  

  return (
    <div className="instanceCreatePage">
      <div className="white-box semi-header">새로운 인스턴스 생성</div>
      <div className="instanceInfo" style={{ overflow: "auto" }}></div>
      <div className="white-box">
        <FloatingLabel
          controlId="floatingSelectCloudId"
          label="클릭하여 스펙을 선택하세요"
        >
          <Form.Select
            aria-label="Floating label select example"
            onChange={(e) => {
              setSelectedCloudId(e.target.value);
            }}
          >
            <option>생성할 스펙을 선택하세요</option>
            {cloudArray.map((cloud) => (
              <option key={cloud.cloudId} value={cloud.cloudId}>
                서버명: {cloud.serverName}, 운영체제:{" "}
                {cloud.component.operatingSystem}, RAM: {cloud.component.ram},
                SSD: {cloud.component.ssd}, 일별요금: {cloud.dailyFee.fee}
              </option>
            ))}
          </Form.Select>
        </FloatingLabel>

        <div className="blank"></div>

        <div className="bottom-box">
          <h2>선택한 인스턴스 예상 요금</h2>
        </div>
        <Form.Range
          value={days}
          onChange={(e) => {
            setDays(e.target.value);
            calculateEstimatedFee();
          }}
          min={0}
          max={365}
        />
        <div className="prograss-bar">
          사용 날짜 : {days} 일 / 예상 요금: {formatCurrency(estimatedFee)} 원
        </div>

        <div className="form-btn">
          <Button
            onClick={handlePostRequest}
            variant="secondary"
            className="mt-4"
          >
            생성
          </Button>
        </div>
      </div>
    </div>
  );
}

export default InstanceCreatePage;
