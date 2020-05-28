import React from 'react';
import Page from 'components/Page';
import {Button, Card, CardBody, CardColumns, CardFooter, CardImg, CardSubtitle, CardText, CardTitle} from 'reactstrap';
import bg11Image from 'assets/img/bg/background_1920-11.jpg';
import axios from "axios";
import Table from "reactstrap/es/Table";


class PreviousBookingsPage extends React.Component{

    constructor(props)
    {
        super(props);
        this.state = {
            bookings: []
        };
    }


    toggle = (event) =>{
        this.setState({
            [event.target.name]: event.target.value
        });

    };

    componentDidMount() {

        {
            axios.get(//`http://localhost:8081/getCompletedBookings/`+sessionStorage.getItem("id"))
                process.env.REACT_APP_API_URL+`/getCompletedBookings/`+sessionStorage.getItem("id"))
                .then(res => {
                    console.log(res.data)
                    this.setState({bookings: res.data});
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
                <Page title="Bookings" breadcrumbs={[{ name: 'Previous', active: true }]}>
                    {this.state.bookings.map(booking =>
                        <Card>
                            <CardBody>
                                <CardTitle> <h4>Booking Id: {booking.bookingId}</h4></CardTitle>

                                <Table>
                                    <tbody>
                                    <tr className="table-active">
                                        <td scope="row">Professional Name</td>
                                        {booking.professionalServices.map((prof =>
                                                <td>{prof.professional.professionalName}</td>
                                        ))}
                                    </tr>
                                    <tr className="table-active">
                                        <td scope="row">Service Description</td>
                                        {booking.professionalServices.map((prof =>
                                                <td>{prof.service.serviceDesc}</td>
                                        ))}
                                    </tr>
                                    <tr className="table-active">
                                        <td scope="row">Service Cost</td>
                                        {booking.professionalServices.map((professional =>
                                                <td>
                                                    {professional.serviceCost}
                                                </td>
                                        ))}
                                    </tr>
                                    <tr className="table-active">
                                        <td scope="row">Service Commission</td>
                                        {booking.professionalServices.map((prof =>
                                                <td>{prof.service.serviceCommission}</td>
                                        ))}
                                    </tr>
                                    <tr className="table-active">
                                        <td scope="row">Booking Comments</td>
                                        <td>{booking.bookingComments}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <td scope="row">Booking Status</td>
                                        <td>{booking.bookingStatus}</td>
                                    </tr>
                                    </tbody>
                                </Table>
                            </CardBody>
                        </Card>
                    )}
                </Page>
            </div>
        );

    }
}

export default PreviousBookingsPage;

