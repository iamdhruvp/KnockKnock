import logo200Image from 'assets/img/logo/logo_200.png';
import PropTypes from 'prop-types';
import React from 'react';
import {Button, ButtonGroup, Form, FormGroup, Input, Label} from 'reactstrap';
import axios from "axios";

class AuthForm extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
        mobileNo: "",
        password: "",
        customerName: "",
        customerGender: "",
        customerEmail: "",
        loginErrors: "",
        registrationErrors: ""
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event) {
      this.setState({
          [event.target.name]: event.target.value
      });
  }

  get isLogin() {
    return this.props.authState === STATE_LOGIN;
  }

  get isSignup() {
    return this.props.authState === STATE_SIGNUP;
  }

  changeAuthState = authState => event => {
    event.preventDefault();

    this.props.onChangeAuthState(authState);
  };

  handleSubmit = event => {
    if(this.isLogin){
      const { mobileNo, password } = this.state;
      axios.post(
        "http://localhost:8081/login",
        {
          mobileNo: mobileNo,
          password: password
        })
        .then(response => {
            console.log("login Response", response)
            console.log("login Response Data", response.data)
            console.log("login Response Data Status", response.data.status)
            if (response.data.status) {
                this.props.handleSuccessfulAuth(response.data);
            }
        })
        .catch(error => {
            console.log("login error", error);
        });  
    } else if(this.isSignup){
      const {customerName, customerGender, customerEmail, password, mobileNo} = this.state;
      axios
          .post(
              "http://localhost:8081/postCustomer",
              {
                  customerName: customerName,
                  customerGender: customerGender,
                  customerEmail: customerEmail,
                  password: password,
                  mobileNo: mobileNo
              }
              // ,
              // { withCredentials: true }
          )
          .then(response => {
              console.log("Registration Response", response)
              console.log("Registration Response Data", response.data)
              console.log("Registration Response Data Status", response.data.status)

              if (response.data.status) {
                  this.props.handleSuccessfulAuth(response.data);
              }
          })
          .catch(error => {
              console.log("registration error", error);
          });
    }
    
    event.preventDefault();
  };

  renderButtonText() {
    const { buttonText } = this.props;

    if (!buttonText && this.isLogin) {
      return 'Login';
    }

    if (!buttonText && this.isSignup) {
      return 'Signup';
    }

    return buttonText;
  }

  render() {
    const {
      showLogo,
      usernameLabel,
      usernameInputProps,
      passwordLabel,
      passwordInputProps,
      nameLabel,
      nameInputProps,
      emailLabel,
      emailInputProps,
      genderLabel,
      genderInputProps,
      children,
      onLogoClick,
    } = this.props;

    return (
      <Form onSubmit={this.handleSubmit}>
        {showLogo && (
          <div className="text-center pb-4">
            <img
              src={logo200Image}
              className="rounded"
              style={{ width: 60, height: 60, cursor: 'pointer' }}
              alt="logo"
              onClick={onLogoClick}
            />
          </div>
        )}
        <FormGroup>
          <Label for={usernameLabel}>{usernameLabel}</Label>
          <Input {...usernameInputProps}
            value={this.state.mobileNo}
            onChange={this.handleChange}
          />
        </FormGroup>
        <FormGroup>
          <Label for={passwordLabel}>{passwordLabel}</Label>
          <Input
            {...passwordInputProps}
            value={this.state.password}
            onChange={this.handleChange}
          />
        </FormGroup>
        {this.isSignup && (
          <FormGroup>
            <Label for={emailLabel}>{emailLabel}</Label>
            <Input
              {...emailInputProps}
              value={this.state.customerEmail}
              onChange={this.handleChange}
            />
          </FormGroup>
        )}
        {this.isSignup && (
          <FormGroup>
            <Label for={nameLabel}>{nameLabel}</Label>
            <Input
              {...nameInputProps}
              value={this.state.customerName}
              onChange={this.handleChange}
            />
          </FormGroup>
        )}
        {this.isSignup && (
          <FormGroup>
            <Label for={genderLabel}>{genderLabel}</Label>
            <ButtonGroup className="ml-3">
              <Button
                {...genderInputProps}
                value="Female"
                color="primary"
                onClick={() => this.setState({ customerGender: "Female" })}
                active={this.state.customerGender === "Female"}
              >
                Female
              </Button>
              <Button
                {...genderInputProps}
                value="Male"
                color="primary"
                onClick={() => this.setState({ customerGender: "Male" })}
                active={this.state.customerGender === "Male"}
              >
                Male
              </Button>
              <Button
                {...genderInputProps}
                value="Other"
                color="primary"
                onClick={() => this.setState({ customerGender: "Other" })}
                active={this.state.customerGender === "Other"}
              >
                Other
              </Button>
            </ButtonGroup>
          </FormGroup>
        )}
        <FormGroup check>
          <Label check>
            <Input type="checkbox" />{' '}
            {this.isSignup ? 'Agree the terms and policy' : 'Remember me'}
          </Label>
        </FormGroup>
        <hr />
        <Button
          size="lg"
          className="bg-gradient-theme-left border-0"
          block
          onClick={this.handleSubmit}>
          {this.renderButtonText()}
        </Button>

        <div className="text-center pt-1">
          <h6>or</h6>
          <h6>
            {this.isSignup ? (
              <a href="#login" onClick={this.changeAuthState(STATE_LOGIN)}>
                Login
              </a>
            ) : (
              <a href="#signup" onClick={this.changeAuthState(STATE_SIGNUP)}>
                Signup
              </a>
            )}
          </h6>
        </div>

        {children}
      </Form>
    );
  }
}

export const STATE_LOGIN = 'LOGIN';
export const STATE_SIGNUP = 'SIGNUP';

AuthForm.propTypes = {
  authState: PropTypes.oneOf([STATE_LOGIN, STATE_SIGNUP]).isRequired,
  showLogo: PropTypes.bool,
  usernameLabel: PropTypes.string,
  usernameInputProps: PropTypes.object,
  passwordLabel: PropTypes.string,
  passwordInputProps: PropTypes.object,
  nameLabel: PropTypes.string,
  nameInputProps: PropTypes.object,
  emailLabel: PropTypes.string,
  emailInputProps: PropTypes.object,
  genderLabel: PropTypes.string,
  genderInputProps: PropTypes.object,
  
  onLogoClick: PropTypes.func,
};

AuthForm.defaultProps = {
  authState: 'LOGIN',
  showLogo: true,
  usernameLabel: 'Mobile No.',
  usernameInputProps: {
    type: 'mobile',
    placeholder: 'your mobile no',
    name:"mobileNo",
  },
  passwordLabel: 'Password',
  passwordInputProps: {
    type: 'password',
    placeholder: 'your password',
    name: "password",
  },
  nameLabel: 'Full Name',
  nameInputProps: {
    type: 'text',
    placeholder: 'your full name',
    name: "customerName",
  },
  emailLabel: 'Email',
  emailInputProps: {
    type: 'text',
    placeholder: 'your email',
    name: "customerEmail",
  },
  genderLabel: 'Gender',
  genderInputProps: {
    name: "customerGender",
  },
  onLogoClick: () => {},
};

export default AuthForm;
