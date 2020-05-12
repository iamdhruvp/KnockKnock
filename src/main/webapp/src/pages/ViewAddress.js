import React from 'react';
import Page from 'components/Page';
import {Button, Card, CardBody, CardColumns, CardFooter, CardImg, CardSubtitle, CardText, CardTitle} from 'reactstrap';
import bg11Image from 'assets/img/bg/background_1920-11.jpg';
import axios from "axios";
import Table from "reactstrap/es/Table";


class ViewAddress extends React.Component{

    constructor(props)
    {
        super(props);
        this.state = {
            address: []
        };
    }


    toggle = (event) =>{
        this.setState({
            [event.target.name]: event.target.value
        });

    };

    componentDidMount() {

        {
            axios.get(`http://localhost:8081/getaddress`)
                .then(res => {
                    console.log(res.data)
                    this.setState({address: res.data});
                })

        }
    }
    handleEdit(){
        this.props.history.push("/address");
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
                <Page title="Address" >
                    <CardColumns>
                        <Card>
                            <CardBody>
                                <CardTitle>Address  </CardTitle>

                                <Table>
                                    <tbody>
                                    <tr className="table-active">
                                        <th scope="row">Address Name</th>
                                        <td>{this.state.address.addressName}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="row">Address Line</th>
                                        <td>{this.state.address.addressLine}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="row">Address Landmark</th>
                                        <td>{this.state.address.addressLandmark}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="row">Address Pincode</th>
                                        <td>{this.state.address.addressPincode}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="row">Address City</th>
                                        <td>{this.state.address.cityName}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="row">Address State</th>
                                        <td>{this.state.address.cityState}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="row">Address Country</th>
                                        <td>{this.state.address.cityCountry}</td>
                                    </tr>
                                    </tbody>
                                </Table>
                                <Button onclick={this.handleEdit}>Edit</Button>
                            </CardBody>
                        </Card>
                    </CardColumns>
                </Page>
            </div>
        );

    }
}

export default ViewAddress;

