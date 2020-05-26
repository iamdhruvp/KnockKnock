import logo200Image from 'assets/img/logo/logo_200.png';
import PropTypes from 'prop-types';
import React from 'react';
import {
    Button,
    ButtonGroup,
    DropdownItem,
    DropdownMenu,
    DropdownToggle,
    Form,
    FormGroup,
    Input,
    Label, UncontrolledButtonDropdown
} from 'reactstrap';
import axios from "axios";

class BankForm extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            bankAccountNo: "",
            bankAccountName: "",
            bankIFCI: "",
            bank: []
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }
    handleSubmit = () => {

        const {bankAccountNo, bankAccountName, bankIFCI} = this.state;
        axios
            .post(
                'http://localhost:8081/postbank/'+sessionStorage.getItem("id")+"/"+
                sessionStorage.getItem("role"),
                {
                    bankAccountNo: bankAccountNo,
                    bankAccountName: bankAccountName,
                    bankIFCI: bankIFCI
                }
                // ,
                // { withCredentials: true }
            )
            .then(response => {
                console.log("Bank Response", response)
                console.log("Bank Response Data", response.data)
                console.log("Bank Response Data Status", response.data.status)

                if (response.data.status) {
                    this.props.handleSuccessful(response.data);
                }
            })
            .catch(error => {
                console.log("Bank error", error);
            });



    }

    componentDidMount()

    {
        axios.get(`http://localhost:8081/getbank/`+sessionStorage.getItem("id")+"/"+
            sessionStorage.getItem("role"))
            .then(res => {
                console.log(res.data)
                this.setState({bank: res.data});
            })
    }



    handleChange(event) {

        this.setState({ [event.target.name] : event.target.value })
    }


    render() {
        const {
            showLogo,
            bnoLabel,
            bnoInputProps,
            bnameLabel,
            bnameInputProps,
            bifciLabel,
            bifciInputProps,
            children,
            onLogoClick,
        } = this.props;


        return (
            <Form onSubmit={this.handleSubmit}>
                {showLogo && (
                    <div className="text-center pb-4">
                        <img
                            src={logo200Image}
                            className="rounded"
                            style={{ width: 60, height: 60, cursor: 'pointer' }}
                            alt="logo"
                            onClick={onLogoClick}
                        />
                    </div>
                )}

                <FormGroup>
                    <Label for={bnoLabel}>{bnoLabel}</Label>
                    <Input
                        {...bnoInputProps}

                        defaultValue={this.state.bank.bankAccountNo}
                        ref={this.input}
                        onChange={this.handleChange}
                    />
                </FormGroup>

                <FormGroup>
                    <Label for={bnameLabel}>{bnameLabel}</Label>
                    <Input
                        {...bnameInputProps}
                        value={this.state.bank.bankAccountName}
                        onChange={this.handleChange}
                    />
                </FormGroup>

                <FormGroup>
                    <Label for={bifciLabel}>{bifciLabel}</Label>
                    <Input
                        {...bifciInputProps}
                        value={this.state.bank.bankIFCI}
                        onChange={this.handleChange.bind(this)}
                    />
                </FormGroup>



                <FormGroup check>
                    <Label check>
                        <Input type="checkbox" />{' '}
                        'Agree the terms and policy'
                    </Label>
                </FormGroup>
                <hr />
                <Button
                    size="lg"
                    className="bg-gradient-theme-left border-0"
                    block
                    onClick={this.handleSubmit}>
                    Add Bank Details
                </Button>

                {children}
            </Form>
        );
    }
}



BankForm.propTypes = {
    showLogo: PropTypes.bool,
    bnoLabel: PropTypes.string,
    bnoInputProps: PropTypes.object,
    bnameLabel: PropTypes.string,
    bnameInputProps: PropTypes.object,
    bifciLabel: PropTypes.string,
    bifciInputProps: PropTypes.object,
    onLogoClick: PropTypes.func,
};

BankForm.defaultProps = {
    showLogo: true,
    bnoLabel: 'Account Number',
    bnoInputProps: {
        type: 'text',
        placeholder: 'Enter your Name',
        name:"bankAccountNo",
    },
    bnameLabel: 'Account holder Name',
    bnameInputProps: {
        type: 'text',
        placeholder: 'Enter your Name',
        name:"bankAccountName",
    },
    bifciLabel: 'Your IFCI code',
    bifciInputProps: {
        type: 'text',
        placeholder: 'Enter your IFCI code',
        name:"bankIFCI",
    },

    onLogoClick: () => {},
};

export default BankForm;
