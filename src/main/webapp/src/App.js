import {STATE_LOGIN, STATE_SIGNUP, STATE_SIGNUPASPROF} from 'components/AuthForm';
import {EmptyLayout, LayoutRoute,  MainLayout , MainLayout1} from 'components/Layout';
import PageSpinner from 'components/PageSpinner';
import AuthPage from 'pages/AuthPage';
import React from 'react';
import componentQueries from 'react-component-queries';
import {BrowserRouter, Redirect, Route, Switch } from 'react-router-dom';
import './styles/reduction.scss';

const ViewAddress = React.lazy(() => import('pages/ViewAddress'));
const ProfessionalServPage = React.lazy(() => import('pages/ProfessionalServPage'));
const AddressPage = React.lazy(() => import('pages/AddressPage'));
const BankForm = React.lazy(() => import('pages/BankPage'));
const ViewBank = React.lazy(() => import('pages/ViewBank'));
const OnGoingBookingsPage = React.lazy(() => import('pages/OnGoingBookingsPage'));
const ProfessionalView = React.lazy(() => import('pages/ProfessionalView'));
const PreviousBookingsPage = React.lazy(() => import('pages/PreviousBookingsPage'));
const CategoryCardPage = React.lazy(() => import('pages/CategoryCardPage'));


const getBasename = () => {
  return `/${process.env.PUBLIC_URL.split('/').pop()}`;
};

class App extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      loggedInStatus: "NOT_LOGGED_IN",
      user: {}
      };

    this.handleLoginProf = this.handleLoginProf.bind(this);
    this.handleLogin = this.handleLogin.bind(this);
    this.handleLogout = this.handleLogout.bind(this);
  }

  checkLoginStatus() {
    if (
      sessionStorage.getItem("id") !== null &&
      this.state.loggedInStatus === "NOT_LOGGED_IN"
    ) {
      this.setState({
        loggedInStatus: "LOGGED_IN",
        user: sessionStorage.getItem("user")
      });
    } else if (
      !sessionStorage.getItem("id") &
      (this.state.loggedInStatus === "LOGGED_IN")
    ) {
      this.setState({
        loggedInStatus: "NOT_LOGGED_IN",
        user: {}
      });
    }
  }

  componentDidMount() {
    this.checkLoginStatus();
    console.log(this.state.loggedInStatus, "mount");
  }

  componentDidUpdate() {
    //this.checkLoginStatus();
    console.log(this.state.loggedInStatus, "update");
    ///////////////////////Error
  }

  handleLogout() {
    this.setState({
      loggedInStatus: "NOT_LOGGED_IN",
      user: {}
    });
  }

  handleLogin(data) {
    this.setState({
      loggedInStatus: "LOGGED_IN",
      user: data.user
    });
    sessionStorage.setItem("id", data);
    console.log("data", data);
  }

  handleLoginProf(data) {
    this.setState({
      loggedInStatus: "LOGGED_IN",
      user: data.user
    });
    sessionStorage.setItem("id", data);
    console.log("data", data);
  }

  render() {
    return (
      <BrowserRouter basename={getBasename()}>
        
          <Switch>
            <LayoutRoute
              exact
              path="/login"
              layout={EmptyLayout}
              component={props => (
                <AuthPage
                  {...props}

                  handleLoginProf={this.handleLoginProf}
                  handleLogin={this.handleLogin}
                  handleLogout={this.handleLogout}
                  loggedInStatus={this.state.loggedInStatus}
                  authState={STATE_LOGIN}
                />
              )}
            />
            <LayoutRoute
              exact
              path="/signup"
              layout={EmptyLayout}
              component={props => (
                <AuthPage {...props}
                handleLogin={this.handleLogin}
                handleLoginProf={this.handleLoginProf}
                handleLogout={this.handleLogout}
                loggedInStatus={this.state.loggedInStatus}
                authState={STATE_SIGNUP} />
              )}
            />
            <LayoutRoute
                exact
                path="/signupAsProf"
                layout={EmptyLayout}
                component={props => (
                    <AuthPage {...props}
                              handleLoginProf={this.handleLoginProf}
                              handleLogout={this.handleLogout}
                              loggedInStatus={this.state.loggedInStatus}
                              authState={STATE_SIGNUPASPROF} />
                )}
            />


            <Route exact path="/"
                  render={props => (
                    <EmptyLayout>
                      <AuthPage
                        {...props}
                        handleLogin={this.handleLogin}
                        handleLoginProf={this.handleLoginProf}
                        handleLogout={this.handleLogout}
                        loggedInStatus={this.state.loggedInStatus}
                        authState={STATE_LOGIN}
                      />
                    </EmptyLayout>
                  )}
            />





<Route exact path={["/tabul", "/address1","/viewAddress1","/viewBank1","/bank1","/booking1","/bookingHistory1", "/service"]}>

  <MainLayout1 breakpoint={this.props.breakpoint}>
    <React.Suspense fallback={<PageSpinner />}>
      <Route exact path="/tabul" component={ProfessionalView} />
      <Route exact path="/address1" component={AddressPage} />
      <Route exact path="/viewAddress1" component={ViewAddress} />
      <Route exact path="/viewBank1" component={ViewBank} />
      <Route exact path="/bank1" component={BankForm} />
      <Route exact path="/booking1" component={OnGoingBookingsPage} />
      <Route exact path="/bookingHistory1" component={PreviousBookingsPage} />
      <Route exact path="/service" component={ProfessionalServPage} />
    </React.Suspense>
  </MainLayout1>

</Route>

            <Route exact path={["/dashboard","/viewAddress","/viewBank","/bank","/address","/booking","/bookingHistory"]}>
              <MainLayout breakpoint={this.props.breakpoint}>
                <React.Suspense fallback={<PageSpinner />}>
                  <Route exact path="/dashboard" component={CategoryCardPage} />
                  <Route exact path="/category" component={CategoryCardPage} />
                  <Route exact path="/viewAddress" component={ViewAddress} />
                  <Route exact path="/viewBank" component={ViewBank} />
                  <Route exact path="/address" component={AddressPage} />
                  <Route exact path="/bank" component={BankForm} />
                  <Route exact path="/booking" component={OnGoingBookingsPage} />
                  <Route exact path="/bookingHistory" component={PreviousBookingsPage} />
                </React.Suspense>
              </MainLayout>
            </Route>


            <Redirect to="/" />



          </Switch>
        
      </BrowserRouter>
    );
  }
}

const query = ({ width }) => {
  if (width < 575) {
    return { breakpoint: 'xs' };
  }

  if (576 < width && width < 767) {
    return { breakpoint: 'sm' };
  }

  if (768 < width && width < 991) {
    return { breakpoint: 'md' };
  }

  if (992 < width && width < 1199) {
    return { breakpoint: 'lg' };
  }

  if (width > 1200) {
    return { breakpoint: 'xl' };
  }

  return { breakpoint: 'xs' };
};

export default componentQueries(query)(App);
