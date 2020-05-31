import AuthForm, {STATE_LOGIN, STATE_SIGNUP, STATE_SIGNUPASPROF} from 'components/AuthForm';
import React from 'react';
import {Card, Col, Row} from 'reactstrap';

class AuthPage extends React.Component {

  constructor(props) {
    super(props);


    this.handleSuccessfulAuth1 = this.handleSuccessfulAuth1.bind(this);
    this.handleSuccessfulAuth = this.handleSuccessfulAuth.bind(this);
    this.handleLogoutClick = this.handleLogoutClick.bind(this);
  }

  handleSuccessfulAuth(data) {

   if(sessionStorage.getItem("role")==2)
   {
     this.props.handleLoginProf(data);
     console.log("login Response Data Status", sessionStorage.getItem("role"))
     this.props.history.push("/tabul");
   }
   else
   {
     this.props.handleLogin(data);

     this.props.history.push("/dashboard");
   }

  };

  handleSuccessfulAuth1(data) {
    this.props.handleLoginProf(data);
    this.props.history.push("/tabul");

  };

  handleLogoutClick() {
    sessionStorage.removeItem("id");
    sessionStorage.removeItem("user");
    sessionStorage.removeItem("role");
    this.props.handleLogout();
    this.props.history.push("/login");
  }

  handleAuthState = authState => {
    if (authState === STATE_LOGIN) {
      this.props.history.push('/login');
    } else  if (authState === STATE_SIGNUP)
    {
      this.props.history.push('/signup');
    }
    else
    {
      this.props.history.push('/signupAsProf');
    }

  };

  handleLogoClick = () => {
    this.props.history.push('/');
  };

  render() {
    return (
      <Row
        style={{
          height: '100vh',
          justifyContent: 'center',
          alignItems: 'center',
        }}>
        <Col md={6} lg={4}>
          <Card body>

            <AuthForm
              handleSuccessfulAuth={this.handleSuccessfulAuth}
              handleSuccessfulAuth1={this.handleSuccessfulAuth1}
              authState={this.props.authState}
              onChangeAuthState={this.handleAuthState}
              onLogoClick={this.handleLogoClick}
            />
          </Card>
        </Col>
      </Row>
    );
  }
}

export default AuthPage;
