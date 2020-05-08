import AuthForm, {STATE_LOGIN} from 'components/AuthForm';
import React from 'react';
import {Card, Col, Row} from 'reactstrap';

class AuthPage extends React.Component {

  constructor(props) {
    super(props);

    this.handleSuccessfulAuth = this.handleSuccessfulAuth.bind(this);
    this.handleLogoutClick = this.handleLogoutClick.bind(this);
  }

  handleSuccessfulAuth(data) {
    this.props.handleLogin(data);
    this.props.history.push("/dashboard");
  }

  handleLogoutClick() {
    sessionStorage.removeItem("id");
    sessionStorage.removeItem("user");
    this.props.handleLogout();
    this.props.history.push("/login");
  }

  handleAuthState = authState => {
    if (authState === STATE_LOGIN) {
      this.props.history.push('/login');
    } else {
      this.props.history.push('/signup');
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
            <h1>Status: {this.props.loggedInStatus}</h1>
            <button onClick={() => this.handleLogoutClick()}>Logout</button>
            <AuthForm
              handleSuccessfulAuth={this.handleSuccessfulAuth}
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
