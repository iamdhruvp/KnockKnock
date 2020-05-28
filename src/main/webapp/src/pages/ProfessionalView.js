import React from 'react';
import Page from 'components/Page';
import {Button, Card, CardBody, CardColumns, CardFooter, CardImg, CardSubtitle, CardText, CardTitle} from 'reactstrap';
import bg11Image from 'assets/img/bg/background_1920-11.jpg';
import axios from "axios";
import Table from "reactstrap/es/Table";
import {BrowserRouter, Redirect, Route, Switch } from 'react-router-dom';


class ProfessionalView extends React.Component{

    constructor(props)
    {
        super(props);
        this.state = {
            professionals: [],
            bookingId: 0,
            bookingDate: 0,
            status: 0
        };


    }


    toggle = (event) =>{
        this.setState({
            status: 1,
        });
        console.log("++++++++++++++", event.target.value,"++++++++++++++++++")
        this.setState({ [event.target.name] : event.target.value })

    };
    togg = (event) =>{
        this.setState({
            status: 2,
        });
        console.log("++++++++++++++", event.target.value,"++++++++++++++++++")
        this.setState({ [event.target.name] : event.target.value })

    };


            componentDidMount() {

        {

            axios.get(`http://localhost:8081/getPending/`+sessionStorage.getItem("id"))
                .then(res => {
                    console.log(res.data)
                    this.setState({professionals: res.data});
                })
        }
    }
    componentDidUpdate() {




        axios.post(
            'http://localhost:8081/changestatus/' + this.state.bookingId+"/"+this.state.status,
            {})
            .then(response => {
                console.log("Response", response)
                console.log("Response Data", response.data)

                if(this.state.bookingId !== 0)
                window.location.reload();




            })
            .catch(error => {
                console.log("error", error);
            });
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
                        {this.state.professionals.map(professional =>
                        <Card>
                            <CardBody>
                                <CardTitle>Customer Request   </CardTitle>

                                <Table>
                                    <tbody>
                                    <tr className="table-active">
                                        <th scope="row">Booking Serving date</th>
                                        <td>{professional.bookingDate}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="row">Booking Comments</th>
                                        <td>{professional.bookingComments}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="row">Customer Name</th>
                                        <td>{professional.customerName}</td>
                                    </tr>
                                    </tbody>
                                </Table>
                                <Button color='success' onClick={this.toggle} name="bookingId" value={professional.bookingId}>Accept</Button>
                                &nbsp;&nbsp;
                                <Button color='danger' onClick={this.togg} name="bookingId" value={professional.bookingId}>Reject</Button>
                            </CardBody>
                        </Card>
                        )}

                </Page>
            </div>
        );

    }
}

export default ProfessionalView;

