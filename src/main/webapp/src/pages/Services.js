import React from 'react';
import Page from 'components/Page';
import {Button, Card, CardBody, CardColumns, CardFooter, CardImg, CardSubtitle, CardText, CardTitle} from 'reactstrap';
import bg1Image from 'assets/img/users/300_10.jpg';
import bg3Image from 'assets/img/users/300_11.jpg';


class Services extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            first:"Select",
            second:"Select",
            show: false
        };
    }
    
    render() {
        const externalCloseBtn = (
            <button
                className="close"
                style={{
                    position: 'absolute',
                    top: '15px',
                    right: '20px',
                    fontSize: '3rem',
                }}
                onClick={this.toggle}>
                &times;
            </button>
        );
        return (
            <div>

            <Page title="Services" breadcrumbs={[{ name: 'Service', active: true }]}>
                <CardColumns>
                    <Card>
                        <CardImg top width="100%" src={bg1Image} alt="Card image cap" />
                        <CardBody>
                            <CardTitle>Birthday</CardTitle>
                            <CardSubtitle>Professional</CardSubtitle>
                            <CardText></CardText>
                        </CardBody>
                        <CardFooter className="text-muted">
                            <Button color="primary"  active={true} block>{this.state.first}</Button>
                        </CardFooter>
                    </Card>
                    <Card>
                        <CardImg top width="100%" src={bg3Image} alt="Card image cap" />
                        <CardBody>
                            <CardTitle>Event</CardTitle>
                            <CardSubtitle>Professional</CardSubtitle>
                            <CardText></CardText>
                        </CardBody>
                        <CardFooter className="text-muted">
                            <Button color="primary"  active={true} block>{this.state.second}</Button>
                        </CardFooter>
                    </Card>


                </CardColumns>
            </Page>
            </div>
        );
    }
}

export default Services;
