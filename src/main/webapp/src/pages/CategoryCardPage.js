import React from 'react';
import Page from 'components/Page';
import {
    Badge,
    Button,
    Card,
    CardBody,
    CardColumns,
    CardDeck,
    CardFooter,
    CardImg,
    CardSubtitle,
    CardText,
    CardTitle,
    Nav,
    Navbar,
    NavbarText,
    NavItem,
} from 'reactstrap';
import bg11Image from 'assets/img/bg/background_1920-11.jpg';
import axios from "axios";
import Row from "reactstrap/es/Row";
import Col from "reactstrap/es/Col";
import Container from "reactstrap/es/Container";

class CategoryCardPage extends React.Component{
    state = {
        show: 1,
        categories:[],
        selectedCategory:0,
        subcategories:[],
        selectedSubCategory:0,
        services:[],
        selectedServices:[],
        professionals:[],
        selectedProfessional:0

    };
    
    toggle = (event) => {
        this.setState({
            show: this.state.show + 1,
        });
        console.log("++++++++++++++", event.target.value,"++++++++++++++++++")
        this.setState({ [event.target.name] : event.target.value })
    };
    serviceBtnClick(selected) {
        const index = this.state.selectedServices.indexOf(selected);
        if (index < 0) {
            this.state.selectedServices.push(selected);
        } else {
            this.state.selectedServices.splice(index, 1);
        }
        this.setState({ selectedServices: [...this.state.selectedServices] });
        console.log()
    }

    componentDidMount() {
        axios.get(`http://localhost:8081/getCategory`)
            .then(res => {
                console.log(res.data)
                this.setState({ categories : res.data });
            })
    }
    componentDidUpdate() {

        { this.state.show === 2 && this.state.subcategories.length === 0 &&
            axios.get(`http://localhost:8081/getSubCategory/` + this.state.selectedCategory)
                .then(res => {
                    console.log(res.data)
                    this.setState({subcategories: res.data});
                })
        }
        { this.state.show === 3 && this.state.services.length === 0 &&
        axios.get(`http://localhost:8081/getService/` + this.state.selectedSubCategory)
            .then(res => {
                console.log(res.data)
                this.setState({services: res.data});
            })
        }
        { this.state.show === 40 && this.state.professionals.length === 0 &&
            axios.post(`http://localhost:8081/getProfessional/`,
                this.state.selectedServices)
                .then(res => {
                    console.log(res.data)
                    this.setState({services: res.data});
                })
                .catch(error => {
                    console.log("profs error", error);
                });
        }
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
        const Categories = this.state.categories.map((category, i) => (
            <Card>
                <CardImg top width="100%" src={bg11Image} alt="Card image cap" />
                <CardBody>
                    <CardTitle>{category.serviceCategoryName}</CardTitle>
                    <CardSubtitle>Category Id : { category.serviceCategoryId }</CardSubtitle>
                    <CardText>Array Index : { i }</CardText>
                    <Button onClick={this.toggle} name="selectedCategory" value={ category.serviceCategoryId } block>Select</Button>
                </CardBody>
            </Card>
        ));
        const SubCategories = this.state.subcategories.map((subcategory, i) => (
            <Card>
                <CardImg top width="100%" src={bg11Image} alt="Card image cap" />
                <CardBody>
                    <CardTitle>{ subcategory.serviceSubCategoryName }</CardTitle>
                    <CardSubtitle>SubCategory Id : { subcategory.serviceSubCategoryId }</CardSubtitle>
                    <CardText>Desc : { subcategory.serviceSubCategoryDesc }</CardText>
                </CardBody>
                <CardFooter className="text-muted">
                    <Button onClick={this.toggle} name="selectedSubCategory" value={ subcategory.serviceSubCategoryId } block>Select</Button>

                </CardFooter>
            </Card>
        ));
        const Services = this.state.services.map((service, i) => (
            <Card>
                <CardImg top width="100%" src={bg11Image} alt="Card image cap" />
                <CardBody>
                    <CardTitle>{ service.serviceName }</CardTitle>
                    <CardSubtitle>Service Id : { service.serviceId }</CardSubtitle>
                    <CardText>Desc : { service.serviceDesc }</CardText>
                </CardBody>
                <CardFooter className="text-muted">
                    <Container>
                        <Row>
                            <Col>
                                <Button color="secondary"
                                        onClick={() => this.serviceBtnClick(service.serviceId)}
                                        disabled={!this.state.selectedServices.includes(service.serviceId)}
                                        name="selectedServices" value={ service.serviceId } block>Remove</Button>
                            </Col>
                            <Col>
                                <Button color="primary"
                                        onClick={() => this.serviceBtnClick(service.serviceId)}
                                        disabled={this.state.selectedServices.includes(service.serviceId)}
                                        name="selectedServices" value={ service.serviceId } block>Add</Button>
                            </Col>
                    </Row>
                    </Container>
                </CardFooter>
            </Card>

        ));
        const Professionals = this.state.subcategories.map((professional, i) => (
            <Card>
                <CardImg top width="100%" src={bg11Image} alt="Card image cap" />
                <CardBody>
                    <CardTitle>{ professional }</CardTitle>
                    <CardSubtitle>SubCategory Id : { professional }</CardSubtitle>
                    <CardText>Desc : { professional }</CardText>
                </CardBody>
                <CardFooter className="text-muted">
                    <Button onClick={this.toggle} name="selectedSubCategory" value={ professional } block>Select</Button>

                </CardFooter>
            </Card>
        ));
        return (
            <div>
                {this.state.show === 1 &&
                (<Page title="Category" breadcrumbs={[{ name: 'Category', active: true }]}>
                    <CardColumns>{ Categories }</CardColumns>
                </Page>)
                }
                { this.state.show === 2 && (
                <Page title="SubCategory" breadcrumbs={[{ name: 'Category', active: false }, { name: 'SubCategory', active: true }]}>
                        <CardDeck>{ SubCategories }</CardDeck>
                </Page>
                )}
                { this.state.show === 3 && (
                    <Page title="Service" breadcrumbs={[{ name: 'Category', active: false }, { name: 'SubCategory', active: false }, { name: 'Service', active: true }]}>
                        <CardColumns>{ Services }</CardColumns>
                        {this.state.selectedServices.length !== 0 &&
                        <Navbar color="light" light fixed='bottom'>
                            <Nav navbar>
                                <NavItem>
                                    Selected Services(IDs) : {this.state.selectedServices}
                                </NavItem>
                            </Nav>
                            <NavbarText>
                                <Button size="lg" onClick={this.toggle}>
                                    <Badge color="secondary">{this.state.selectedServices.length}</Badge> Continue <b>&rarr;</b></Button>
                            </NavbarText>
                        </Navbar>}
                    </Page>
                )}
                { this.state.show === 4 && (
                    <Page title="Professional" breadcrumbs={[{ name: 'Category', active: false }, { name: 'SubCategory', active: false }, { name: 'Service', active: false },{ name: 'Professional', active: true }]}>
                        <CardColumns>{ Professionals }</CardColumns>
                    </Page>
                )}

            </div>
        );
    }
}

export default CategoryCardPage;
