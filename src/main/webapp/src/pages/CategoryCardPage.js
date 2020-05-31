import React from 'react';
import Page from 'components/Page';
import Header from "../components/Layout/Header";

import {ButtonGroup, Form, FormGroup, Input, Label} from 'reactstrap';
import {
    Badge,
    Button,
    Card,
    CardBody,
    CardColumns,
    CardDeck,
    CardFooter,
    CardImg,
    CardLink,
    CardSubtitle,
    CardText,
    CardTitle,
    ListGroup,
    ListGroupItem,
    Nav,
    Navbar,
    NavbarText,
    NavItem,
} from 'reactstrap';

import axios from "axios";
import Row from "reactstrap/es/Row";
import Col from "reactstrap/es/Col";
import Container from "reactstrap/es/Container";
import bg1Image from 'assets/img/bg/background_640-1.jpg';
import bg18Image from 'assets/img/bg/background_1920-18.jpg';


class CategoryCardPage extends React.Component{
    constructor(props) {
        super(props);

        this.state = {
            show: 1,
            categories:[],
            selectedCategory:0,
            subcategories:[],
            selectedSubCategory:0,
            services:[],
            selectedServices:[],
            professionals:[],
            selectedProfessional:null,
            bookingComments: "",
            professionalServices: [],
            total:0
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);

    }


    toggle = (event) => {
        this.setState({
            show: this.state.show + 1,
        });
        console.log(this.state.show,"----------toggled")
        console.log(this.state,"----------toggled")
        if(this.state.show !== 4){console.log("++++++++++++++", event.target.value,"++++++++++++++++++")}
        {this.state.show !== 4 && this.setState({ [event.target.name] : event.target.value })}
    };
    serviceBtnClick(selected) {
        const index = this.state.selectedServices.indexOf(selected);
        if (index < 0) {
            this.state.selectedServices.push(selected);
        } else {
            this.state.selectedServices.splice(index, 1);
        }
        this.setState({ selectedServices: [...this.state.selectedServices] });
        console.log( this.state.selectedServices,"...........SelectedServiceResponse")
    }

    showDetails(selected) {
        this.setState({ selectedProfessional: this.state.professionals[selected]});
        console.log( this.state.selectedProfessional,"...........SelectedProfessional")
    }

    componentDidMount() {
        axios.get(//
            process.env.REACT_APP_API_URL+`/getCategory`)
            .then(res => {
                console.log(res.data)
                this.setState({ categories : res.data });
            })
    }
    componentDidUpdate() {

        { this.state.show === 2 && this.state.subcategories.length === 0 &&
        axios.get(//`http://localhost:8081/getSubCategory/` + this.state.selectedCategory)
            process.env.REACT_APP_API_URL+`/getSubCategory/` + this.state.selectedCategory)
            .then(res => {
                console.log(res.data)
                this.setState({subcategories: res.data});
            })
        }
        { this.state.show === 3 && this.state.services.length === 0 &&
        axios.get(//`http://localhost:8081/getService/` + this.state.selectedSubCategory)
            process.env.REACT_APP_API_URL+`/getService/` + this.state.selectedSubCategory)
            .then(res => {
                console.log(res.data)
                this.setState({services: res.data});
            })
        }
        { this.state.show === 4 && this.state.professionals.length === 0 &&
        axios.post(//`http://localhost:8081/getProfessional/`,
            process.env.REACT_APP_API_URL+`/getProfessional/`,
            this.state.selectedServices)
            .then(res => {
                console.log(res.data,"--------------response")
                this.setState({professionals: res.data});
            })
            .catch(error => {
                console.log("profs error", error);
            });
        }
    }

    ////......... Booking ..........////
    handleChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    handleSubmit = event => {
        event.preventDefault();

        //this.setState({professionalServices : this.state.selectedProfessional.professionalServices})
        this.state.professionalServices = this.state.selectedProfessional.professionalServices
        console.log(this.state.professionalServices,"...........ProfessionalServices")

        /// ......... filter section .......... ///

        /*
        const filteredProfessional = this.state.professionalServices.filter( p =>
             p.id.serviceServiceId === 3
         )
        console.log(filteredProfessional,".........filterProfessional")
        */
        /// ........ filter section ....... ///


        const {bookingComments,professionalServices} = this.state;

        axios.post(
            //"http://localhost:8081/addBooking/"+sessionStorage.getItem("id"),
            process.env.REACT_APP_API_URL+"/addBooking/"+sessionStorage.getItem("id"),
            {
                bookingComments: bookingComments,
                professionalServices: professionalServices
            }
        )
            .then(response => {
                console.log(response)
                console.log(response.data)
                console.log(response.data.status)
            })
            .catch(error => {
                console.log(this.state.bookingComments,this.state.professionalServices);
                console.log("booking error", error);
            });

        this.props.history.push("/dashboard");
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
                <CardImg top width="100%" src={require(`./img/im${i}.jpeg`)}  alt="Card image cap" />
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
                <CardImg top width="100%" src={require(`./img/img${i}.jpeg`)} alt="Card image cap" />
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
                <CardImg top width="100%" src="/img/screenshots/imm.jpg" alt="Card image cap" />
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
        const Professionals = this.state.professionals.map((professional, i) => (
            <Card className="flex-row">
                <CardImg
                    className="card-img-left"
                    src={require(`./img/imgg${i}.jpg`)}
                    style={{ width: 'auto', height: 170 }}
                />
                <CardBody>
                    <CardTitle>{professional.professionalName} <Badge style={{float: 'right'}} color="secondary">Experience: {professional.professionalExperience} Yrs</Badge> </CardTitle>
                    <CardSubtitle>{professional.professionalGender}</CardSubtitle>
                    <CardText>{professional.professionalEmail}</CardText>
                    <Button onClick={() => this.showDetails(i)} style={{float: 'right'}} name="selectedProfessional" value={ professional.professionalId } >View Details ></Button>
                </CardBody>
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
                        <Row>
                            <Col md={6} sm={6} xs={12} className="mb-3">
                                { Professionals }
                            </Col>
                            { this.state.selectedProfessional !== null && (<Col md={6} sm={6} xs={12} className="mb-3">
                                <Card>
                                    <CardImg top src={bg18Image} />
                                    <CardBody>
                                        <CardTitle>Photographer: {this.state.selectedProfessional.professionalName}</CardTitle>
                                        <CardText>

                                        </CardText>
                                    </CardBody>
                                    <ListGroup flush>
                                        <ListGroupItem>
                                            {this.state.selectedProfessional.professionalServices.map((s, i) =>(
                                                <div>{ this.state.selectedServices.includes(s.service.serviceId) && (<Row>
                                                    <Col>service: {s.service.serviceName}</Col>
                                                    <Col>Description: {s.service.serviceDesc}</Col>
                                                    <Col>Price: {s.serviceCost}</Col>
                                                </Row>)}
                                                </div>
                                            ))}
                                        </ListGroupItem>

                                    </ListGroup>
                                    <CardBody>
                                        <FormGroup>
                                            <Label for="bookingComments">Comments</Label>
                                            <Input type="textarea" name="bookingComments" value={this.state.bookingComments} onChange={this.handleChange}/>
                                        </FormGroup>
                                        <Button onClick={this.handleSubmit}>Book Now</Button>
                                    </CardBody>
                                </Card>
                            </Col>)}
                        </Row>
                    </Page>
                )}

            </div>
        );
    }
}

export default CategoryCardPage;
