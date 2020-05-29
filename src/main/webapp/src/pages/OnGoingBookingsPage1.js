import React from 'react';
import Page from 'components/Page';
import {Button, Card, CardBody, CardColumns, CardFooter, CardImg, CardSubtitle, CardText, CardTitle} from 'reactstrap';
import bg11Image from 'assets/img/bg/background_1920-11.jpg';
import axios from "axios";
import Table from "reactstrap/es/Table";


class OnGoingBookingsPage1 extends React.Component{

    constructor(props)
    {
        super(props);
        this.state = {
            professionals: [],
            profs: [],
            bookingId: 0,
            bookingDate: 0,
            status: 1 //////// 0 for pending, 1 for accept , 2 for reject , 3 for complete
        };
    }


    accept = (event) =>{
        this.setState({
            status: 3,
        });
        this.setState({ [event.target.name] : event.target.value })
        console.log("++++++++++++++", event.target.value,"++++++++++++++++++")
    };


    componentDidMount() {

        {
            axios.get(//`http://localhost:8081/getP`)
                process.env.REACT_APP_API_URL+`/getProfs/`+sessionStorage.getItem("id")+`/`+this.state.status)
                .then(res => {
                    console.log(res.data)
                    this.setState({profs: res.data});
                })

        }
    }

    componentDidUpdate() {

        axios.post(
            process.env.REACT_APP_API_URL+'/changestatus/' + this.state.bookingId+"/"+this.state.status,
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
                <Page title="Bookings" breadcrumbs={[{ name: 'OnGoing', active: true }]}>
                    {this.state.profs.map(p =>
                        <Card>
                            <CardBody>
                                <CardTitle>Accepted Requests   </CardTitle>

                                <Table>
                                    <tbody>
                                    <tr className="table-active">
                                        <th scope="row">Customer Name</th>
                                        <td>{p.customer.customerName}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="row">Booking Date</th>
                                        <td>{p.bookingDate}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="row">Booking Serving Date</th>
                                        <td>{p.bookingServStartDate}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="rorofessionalw">Booking Comments</th>
                                        <td>{p.bookingComments}</td>
                                    </tr>
                                    </tbody>
                                </Table>
                                <Button color='success' onClick={this.accept} name="bookingId" value={p.bookingId}>Complete</Button>
                            </CardBody>
                        </Card>
                    )}

                </Page>
            </div>
        );

    }
}

export default OnGoingBookingsPage1;

