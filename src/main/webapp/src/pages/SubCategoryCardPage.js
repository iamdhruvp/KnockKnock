import React from 'react';
import Page from 'components/Page';
import {
    Button,
    Card,
    CardBody,
    CardColumns,
    CardFooter,
    CardImg,
    CardSubtitle,
    CardText,
    CardTitle,
    Modal,
    ModalBody
} from 'reactstrap';
import bg1Image from 'assets/img/users/300_1.jpg';
import bg3Image from 'assets/img/users/300_2.jpg';
import Services from "./Services";


class SubCategoryCardPage extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            first:"Select",
            second:"Select",
            show: false
        };
    }
    toggle = () => {
        this.setState({
            show: !this.state.show,
        });
        if(this.state.show){this.props.toggle()}
    };
    
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

            <Page title="Events" breadcrumbs={[{ name: 'Event', active: true }]}>
                <CardColumns>
                    <Card>
                        <CardImg top width="100%" src={bg1Image} alt="Card image cap" />
                        <CardBody>
                            <CardTitle>Birthday</CardTitle>
                            <CardSubtitle>Professional</CardSubtitle>
                            <CardText></CardText>
                        </CardBody>
                        <CardFooter className="text-muted">
                            <Button color="primary"  active={true} onClick={this.toggle} block>{this.state.first}</Button>
                            <Modal
                                isOpen={this.state.show}
                                toggle={this.toggle}
                                size="xl"
                                backdrop="static"
                                backdropClassName="modal-backdrop-light"
                                external={externalCloseBtn}
                                centered>
                                <ModalBody>
                                    <Services
                                        toggle={this.toggle}
                                    />
                                </ModalBody>
                            </Modal>
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

export default SubCategoryCardPage;
