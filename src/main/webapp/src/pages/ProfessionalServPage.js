import ProfessionalServForm, {STATE_LOGIN} from 'components/ProfessionalServForm';
import React from 'react';
import {Card, Col, Row} from 'reactstrap';

class ProfessionalServPage extends React.Component {

    constructor(props) {
        super(props);

        this.handleSucces = this.handleSucces.bind(this);
        this.handleLogoutClick = this.handleLogoutClick.bind(this);
    }

    handleSucces(data) {

            this.props.history.push("/tabul");


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
                        <h1>Status: {this.props.loggedInStatus}</h1>
                        <button onClick={() => this.handleLogoutClick()}>CLOSE</button>
                        <ProfessionalServForm
                            handleSucces={this.handleSucces}
                            onLogoClick={this.handleLogoClick}
                        />
                    </Card>
                </Col>
            </Row>
        );
    }
}

export default ProfessionalServPage;
