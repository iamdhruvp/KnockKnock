import React from 'react';
import Page from 'components/Page';
import {Button, Card, CardBody, CardColumns, CardFooter, CardImg, CardSubtitle, CardText, CardTitle} from 'reactstrap';
import bg11Image from 'assets/img/bg/background_1920-11.jpg';
import axios from "axios";
import Table from "reactstrap/es/Table";


class ViewBank extends React.Component{

    constructor(props)
    {
        super(props);
        this.state = {
            bank: []
        };
    }


    toggle = (event) =>{
        this.setState({
            [event.target.name]: event.target.value
        });

    };

    componentDidMount() {

        {
            axios.get(`http://localhost:8081/getbank`+sessionStorage.getItem("id"))
                .then(res => {
                    console.log(res.data)
                    this.setState({bank: res.data});
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
                <Page title="Bank" >
                    <CardColumns>
                        <Card>
                            <CardBody>
                                <CardTitle>Bank Details  </CardTitle>

                                <Table>
                                    <tbody>
                                    <tr className="table-active">
                                        <th scope="row">Account Holder Name</th>
                                        <td>{this.state.bank.bankAccountName}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="row">Account No</th>
                                        <td>{this.state.bank.bankAccountNo}</td>
                                    </tr>
                                    <tr className="table-active">
                                        <th scope="row">IFCI code</th>
                                        <td>{this.state.bank.bankIFCI}</td>
                                    </tr>
                                    </tbody>
                                </Table>
                            </CardBody>
                        </Card>
                    </CardColumns>
                </Page>
            </div>
        );

    }
}

export default ViewBank;

