import React from 'react';
import Page from 'components/Page';
import {Button, Card, CardBody, CardColumns, CardFooter, CardImg, CardSubtitle, CardText, CardTitle} from 'reactstrap';
import bg11Image from 'assets/img/bg/background_1920-11.jpg';
import axios from "axios";
import Table from "reactstrap/es/Table";


class ProfessionalView extends React.Component{

    constructor(props)
    {
        super(props);
        this.state = {
            professional: []
        };
    }


    toggle = (event) =>{
        this.setState({
            [event.target.name]: event.target.value
        });

    };

    componentDidMount() {

        {
            axios.get(//`http://localhost:8081/getP`)
                process.env.REACT_APP_API_URL+`/getP`)
                .then(res => {
                    console.log(res.data)
                    this.setState({professional: res.data});
                })

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



        return (
            <div>
                <Page title="Professional" breadcrumbs={[{ name: 'ServiceRequest', active: true }]}>
                    <CardColumns>
                        <Card>
                            <CardBody>
                                <CardTitle>Customer Request   </CardTitle>

                                <Table>
                                    <tbody>
                                    <tr className="table-active">
                                        <th scope="row">Customer Name</th>
                                        <td>{this.state.professional.customerName}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="row">Customer Address</th>
                                        <td>{this.state.professional.customerAddesss}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="row">Service Description</th>
                                        <td>{this.state.professional.customerDescription}</td>
                                    </tr>
                                    </tbody>
                                </Table>
                                <Button color='success' onclick={this.handleAccept}>Accept</Button>
                                &nbsp;&nbsp;
                                <Button color='danger' onclick={this.handleAccept}>Reject</Button>
                            </CardBody>
                        </Card>
                    </CardColumns>
                </Page>
            </div>
        );

    }
}

export default ProfessionalView;

