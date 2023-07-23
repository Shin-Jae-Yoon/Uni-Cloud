import React, {useState} from 'react';
import {Switch, Redirect, Route} from 'react-router-dom';
import LoginPage from './LoginPage';
import InstancePage from './InstancePage';

function Routes() {
    const [isAuthorized, setisAuthorized] = useState(false);
    const [showID, setshowID] = useState('');

    return (
        <div>
          <Switch>
            <Route exact path="/">
              {isAuthorized ? (
                <Redirect to="/instance" />
              ) : (
                <LoginPage
                  setisAuthorized={setisAuthorized}
                  setshowID={setshowID}
                />
              )}
            </Route>
            <Route exact path="/instance">
              {isAuthorized ? (
                <InstancePage
                  setisAuthorized={setisAuthorized}
                  showID={showID}
                />
              ) : (
                <Redirect to="/" />
              )}
            </Route>
          </Switch>
        </div>
      );
    }

export default Routes;