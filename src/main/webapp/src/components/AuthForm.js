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
        registrationErrors: "",
        professionalGSTNo: "",
        professionalBirthDate: "",
        professionalExperience: "",
        iscust: ""
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

    get isSignupAsProf() {
        return this.props.authState === STATE_SIGNUPASPROF;
    }

  changeAuthState = authState => event => {
    event.preventDefault();

    this.props.onChangeAuthState(authState);
  };

  handleSubmit = event => {
    if(this.isLogin){
      const { mobileNo, password,iscust } = this.state;
      axios.post(
        //'http://localhost:8081/verifyCustomer/'+this.state.iscust,
          process.env.REACT_APP_API_URL+'/verifyCustomer/'+this.state.iscust,
        {
          mobileNo: mobileNo,
          password: password
        })
        .then(response => {
            console.log("login Response", response)
            console.log("login Response Data", response.data)
            console.log("login Response Data Status", iscust)
            if(response.status===200 ) {
                sessionStorage.setItem("role", iscust);
                this.props.handleSuccessfulAuth(response.data);
            }

        })
        .catch(error => {
            console.log("login error", error);
        });  
    }
    else if(this.isSignup){
      const {customerName, customerGender, customerEmail, password, mobileNo} = this.state;
      axios
          .post(
              //"http://localhost:8081/postCustomer",
              process.env.REACT_APP_API_URL+"/postCustomer",
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

              if (response.status=== 200) {
                  this.props.handleSuccessfulAuth(response.data);
              }
          })
          .catch(error => {
              console.log("registration error", error);
          });
    }
    else if(this.isSignupAsProf){
        const {customerName, customerGender, customerEmail, password, mobileNo,professionalGSTNo,
            professionalBirthDate,
            professionalExperience} = this.state;
        axios
            .post(
                //"http://localhost:8081/postProfessional",
                process.env.REACT_APP_API_URL+"/postProfessional",
                {
                    customerName: customerName,
                    customerGender: customerGender,
                    customerEmail: customerEmail,
                    password: password,
                    mobileNo: mobileNo,
                    professionalGSTNo: professionalGSTNo,
                    professionalBirthDate: professionalBirthDate,
                    professionalExperience: professionalExperience
                }
                // ,
                // { withCredentials: true }
            )
            .then(response => {
                console.log("Registration Response", response)
                console.log("Registration Response Data", response.data)
                console.log("Registration Response Data Status", response.status)

                if (response.status === 200) {
                    this.props.handleSuccessfulAuth1(response.data);
                }
            })
            .catch(error => {
                console.log("registration error", error);
            });
    };
    
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
      if (!buttonText && this.isSignupAsProf) {
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
        iscustLabel,
      iscustInputProps,
        professionalGSTNoLabel,
        professionalGSTNoInputProps,
        professionalBirthDateLabel,
        professionalBirthDateInputProps,
        professionalExperienceLabel,
        professionalExperienceInputProps,
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
              style={{ width: 60, height: 60, cursor: 'pointer'}}
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
          {this.isLogin && (
              <FormGroup>
                  <Label for={iscustLabel}>{iscustLabel}</Label>
                  <ButtonGroup className="ml-3">
                      <Button
                          {...iscustInputProps}
                          value="1"
                          color="primary"
                          onClick={() => this.setState({ iscust: "1" })}
                          active={this.state.iscust === "1"}
                      >
                          Customer
                      </Button>
                      <Button
                          {...iscustInputProps}
                          value="2"
                          color="primary"
                          onClick={() => this.setState({ iscust: "2" })}
                          active={this.state.iscust === "2"}
                      >
                          Professional
                      </Button>
                  </ButtonGroup>
              </FormGroup>
          )}
        {(this.isSignup || this.isSignupAsProf) && (
          <FormGroup>
            <Label for={emailLabel}>{emailLabel}</Label>
            <Input
              {...emailInputProps}
              value={this.state.customerEmail}
              onChange={this.handleChange}
            />
          </FormGroup>
        )}
        {(this.isSignup || this.isSignupAsProf) && (
          <FormGroup>
            <Label for={nameLabel}>{nameLabel}</Label>
            <Input
              {...nameInputProps}
              value={this.state.customerName}
              onChange={this.handleChange}
            />
          </FormGroup>
        )}
        {(this.isSignup || this.isSignupAsProf)  && (
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
          {this.isSignupAsProf && (
              <FormGroup>
                  <Label for={professionalGSTNoLabel}>{professionalGSTNoLabel}</Label>
                  <Input
                      {...professionalGSTNoInputProps}
                      value={this.state.professionalGSTNo}
                      onChange={this.handleChange}
                  />
              </FormGroup>
          )}
          {this.isSignupAsProf && (
              <FormGroup>
                  <Label for={professionalExperienceLabel}>{professionalExperienceLabel}</Label>
                  <Input
                      {...professionalExperienceInputProps}
                      value={this.state.professionalExperience}
                      onChange={this.handleChange}
                  />
              </FormGroup>
          )}
          {this.isSignupAsProf && (
              <FormGroup>
                  <Label for={professionalBirthDateLabel}>{professionalBirthDateLabel}</Label>
                  <Input
                      {...professionalBirthDateInputProps}
                      type = "date"
                      value={this.state.professionalBirthDate}
                      onChange={this.handleChange}
                  />
              </FormGroup>

          )}

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
              {this.isSignupAsProf &&
              ( <a href="#login" onClick={this.changeAuthState(STATE_LOGIN)}>
                  Login
              </a>
                  )}
          </h6>
            <h6>


                { this.isLogin &&
                  (<a href="#signup" onClick={this.changeAuthState(STATE_SIGNUP)}>
                      Signup
                  </a>
                  )}
            </h6>
            <h6>

                {this.isLogin && (
                  <a href="#signup" onClick={this.changeAuthState(STATE_SIGNUPASPROF)}>
                  Signup as professional
                  </a> )}
            </h6>
            <h6>



                {this.isSignup &&
                ( <a href="#login" onClick={this.changeAuthState(STATE_LOGIN)}>
                      Login
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
export const STATE_SIGNUPASPROF = 'SIGNUPASPROF';

AuthForm.propTypes = {
  authState: PropTypes.oneOf([STATE_LOGIN, STATE_SIGNUP, STATE_SIGNUPASPROF]).isRequired,
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
    iscustLabel: PropTypes.string,
    iscustInputProps: PropTypes.object,
    professionalGSTNoLabel: PropTypes.string,
    professionalGSTNoInputProps: PropTypes.object,

    professionalBirthDateLabel: PropTypes.string,
    professionalBirthDateInputProps: PropTypes.object,
    professionalExperienceLabel: PropTypes.string,
    professionalExperienceInputProps: PropTypes.object,
  
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
    professionalGSTNoLabel: 'professional gst no',
    professionalGSTNoInputProps: {
        name: "professionalGSTNo",
    },
    professionalExperienceLabel: 'professional experience',
    professionalExperienceInputProps: {
        name: "professionalExperience",
    },
    professionalBirthDateLabel: 'professional birthdate',
    professionalBirthDateInputProps: {
        name: "professionalBirthDate",
    },

    iscustLabel: 'User-Role',
    iscustInputProps: {
        name: "iscust",
    },

  onLogoClick: () => {},
};

export default AuthForm;
