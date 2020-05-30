import AddressForm, {STATE_LOGIN} from 'components/AddressForm';
import React from 'react';
import {Card, Col, Row} from 'reactstrap';

class AddressPage extends React.Component {

    constructor(props) {
        super(props);

        this.handleSuccess = this.handleSuccess.bind(this);
        this.handleLogoutClick = this.handleLogoutClick.bind(this);
    }

    handleSuccess(data) {
        if(sessionStorage.getItem("role")==2)
        {

            console.log("login Response Data Status", sessionStorage.getItem("role"))
            this.props.history.push("/tabul");
        }
        else
        {

            this.props.history.push("/dashboard");
        }

    }

    handleLogoutClick() {
        this.props.handleLogout();
        this.props.history.push("/dashboard");
    }


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
                        {/*
                        <h1>Status: {this.props.loggedInStatus}</h1>
                        <button onClick={() => this.handleLogoutClick()}>CLOSE</button>
                        */}
                        <AddressForm
                            handleSuccess={this.handleSuccess}
                            onLogoClick={this.handleLogoClick}
                        />
                    </Card>
                </Col>
            </Row>
        );
    }
}

export default AddressPage;
