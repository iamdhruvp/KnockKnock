import React from 'react';
import Page from 'components/Page';
import {Button, Card, CardBody, CardColumns, CardFooter, CardImg, CardSubtitle, CardText, CardTitle} from 'reactstrap';
import bg11Image from 'assets/img/bg/background_1920-11.jpg';
import axios from "axios";
import Table from "reactstrap/es/Table";


class ViewAddress extends React.Component{
    state = {
        show: 1,
        addresses:[],
        selectedAddress: 0,
        selectedCustomer: 1
    };

    toggle = (event) =>{
        this.setState({
            [event.target.name]: event.target.value
        });

    };

    componentDidMount() {

        {
            axios.get(`http://localhost:8081/getAddress/`)
                .then(res => {
                    console.log(res.data)
                    this.setState({addresses: res.data});
                })
        }
    }
    handleChange(){
        this.props.history.push("/category");
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

        const Addresses = this.state.addresses.map((address, i) => (
            <Card>
                <CardBody>
                    <CardTitle>Address {i} </CardTitle>

                    <Table>
                        <tbody>
                        <tr className="table-active">
                            <th scope="row">Address Name</th>
                            <td>{address.addressName}</td>
                        </tr>
                        <tr className="table-active">
                            <th scope="row">Address Line</th>
                            <td>{address.addressLine}</td>
                        </tr>
                        </tbody>
                    </Table>
                    <Button >Edit</Button>
                </CardBody>
            </Card>
        ));
        return (
            <div>
                <Page title="Addresses"  breadcrumbs={[{ name: 'Address', active: true }]}>
                    <CardColumns>
                        {Addresses}
                    </CardColumns>
                </Page>
            </div>
        );

    }
}

export default ViewAddress;

