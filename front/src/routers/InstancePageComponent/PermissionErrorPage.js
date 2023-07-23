import React from "react";
import "../../scss/InstancePageComponent/PermissionErrorPage.scss";

function PermissionErrorPage() {

  return (
    <div className="permissionErrorPage">
      <div className="white-box semi-header">접근 권한 ❌</div>
      <div className="instanceInfo" style={{ overflow: "auto" }}></div>
      <div className="white-box">
        <p className="error-body">
            접근할 수 없습니다. 관리자에게 문의하세요.
        </p>
      </div>
    </div>
  );
}

export default PermissionErrorPage;