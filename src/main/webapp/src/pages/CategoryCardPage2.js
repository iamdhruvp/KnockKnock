import React from 'react';
import Page from 'components/Page';
import {
    Button,
    Card,
    CardBody,
    CardColumns,
    CardImg,
    CardSubtitle,
    CardText,
    CardTitle,
    Modal,
    ModalBody
} from 'reactstrap';
import bg11Image from 'assets/img/bg/background_1920-11.jpg';
import SubCategoryCardPage from "./SubCategoryCardPage";

class CategoryCardPage2 extends React.Component{
    state = {
        show: false
    };
    
    toggle = () => {
        this.setState({
            show: !this.state.show,
        });
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
            <Page title="Category" breadcrumbs={[{ name: 'Category', active: true }]}>
                <CardColumns>
                    <Card>
                        <CardImg top width="100%" src={bg11Image} alt="Card image cap" />
                        <CardBody>
                            <CardTitle>Photography</CardTitle>
                            <CardSubtitle>Professional</CardSubtitle>
                            <CardText>This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</CardText>
                            <Button onClick={this.toggle}>Button</Button>
                            <Modal
                                isOpen={this.state.show}
                                toggle={this.toggle}
                                size="xl"
                                backdrop="static"
                                backdropClassName="modal-backdrop-light"
                                external={externalCloseBtn}
                                centered>
                                <ModalBody>
                                    <SubCategoryCardPage
                                        toggle={this.toggle}
                                    />
                                </ModalBody>
                            </Modal>
                        </CardBody>
                    </Card>
                    <Card>
                        <CardImg top width="100%" src={bg11Image} alt="Card image cap" />
                        <CardBody>
                            <CardTitle>Lawyer</CardTitle>
                            <CardSubtitle>Professional</CardSubtitle>
                            <CardText>This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</CardText>
                            <Button>Button</Button>
                        </CardBody>
                    </Card>


                </CardColumns>
            </Page>
        );
    }
}

export default CategoryCardPage2;
