import BankForm from 'components/BankForm';
import React from 'react';
import {Card, Col, Row} from 'reactstrap';

class BankPage extends React.Component {

    constructor(props) {
        super(props);

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

                    <BankForm
                        handleSuccessfulAuth={this.handleSuccessfulAuth}
                        onLogoClick={this.handleLogoClick}
                    />
                </Card>
                </Col>
            </Row>
        );
    }
}

export default BankPage;
