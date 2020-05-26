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

class AddressForm extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            addressName: "",
            addressLine: "",
            addressLandmark: "",
            addressPincode: "",
            cityName: "",
            cityState: "",
            cityCountry: "",
            isDefaultAddress: "",
            loginErrors: "",
            address: [],
            countries: [],
            selectedCountry: 0,
            states: [],
            selectedState: 0,
            cities: [],
            show: 1,
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    toggle = (event) => {
        this.setState({
            show: 2,
        });
        this.setState({
            states: []
        });

        console.log("++++++++++++++", event.target.value,"++++++++++++++++++")
        this.setState({ [event.target.name] : event.target.value })

    };
    togg = (event) => {
        this.setState({
            show: 3,
        });
        this.setState({
            cities: []
        });


        console.log("++++++++++++++", event.target.value,"++++++++++++++++++")
        this.setState({ [event.target.name] : event.target.value })

    };



    handleSubmit = () => {

            const {addressName, addressLine, addressLandmark, addressPincode, cityName, cityState,cityCountry, isDefaultAddress} = this.state;
            axios
                .post(
                    "http://localhost:8081/postAddress/"+sessionStorage.getItem("id")+"/"+sessionStorage.getItem("role"),
                    {
                        addressName: addressName,
                        addressLine: addressLine,
                        addressLandmark: addressLandmark,
                        addressPincode: addressPincode,
                        cityName: cityName,
                        cityState: cityState,
                        cityCountry: cityCountry,
                        isDefaultAddress:  isDefaultAddress

                    }
                    // ,
                    // { withCredentials: true }
                )
                .then(response => {
                    console.log("Address Response", response)
                    console.log("Address Response Data", response.data)
                    console.log("Address Response Data Status", response.data.status)

                    if (response.data.status) {
                        this.props.handleSuccess(response.data);
                    }
                })
                .catch(error => {
                    console.log("Address error", error);
                });



    }

    componentDidMount()

        {
            axios.get(`http://localhost:8081/getaddress/`+sessionStorage.getItem("id")+"/"+sessionStorage.getItem("role"))
                .then(res => {
                    console.log(res.data)
                    this.setState({address: res.data});
                })

            this.state.show === 1 &&
            axios.get(`http://localhost:8081/getCountry`)
                .then(res => {
                    console.log(res.data)
                    this.setState({countries : res.data});
                })


        }

    componentDidUpdate() {

        {
            this.state.show === 2 && this.state.states.length === 0 &&
            axios.get(`http://localhost:8081/getState/` + this.state.selectedCountry)
                .then(res => {
                    console.log(res.data)
                    this.setState({states: res.data});
                })
        }

        {
            this.state.show === 3 && this.state.cities.length === 0 &&
            axios.get(`http://localhost:8081/getCity/` + this.state.selectedState)
                .then(res => {
                    console.log(res.data)
                    this.setState({cities: res.data});
                })
        }


    }

    handleChange(event) {

        this.setState({ [event.target.name] : event.target.value })
    }


        render() {
        const {
            showLogo,
            anameLabel,
            anameInputProps,
            alineLabel,
            alineInputProps,
            alandmarkLabel,
            alandmarkInputProps,
            apincodeLabel,
            apincodeInputProps,
            acityLabel,
            acityInputProps,
            astateLabel,
            astateInputProps,
            acountryLabel,
            acountryInputProps,
            isdefaultLabel,
            isdefaultInputProps,
            children,
            onLogoClick,
        } = this.props;

            const Countries = this.state.countries.map((country) => (
    <DropdownItem name="selectedCountry" value={country}

                            onChange={() => this.setState({ cityCountry: {country} })}
                  onClick={this.toggle} >
    {country}
</DropdownItem>
        ));
            const States = this.state.states.map((state) => (


    <DropdownItem onClick={this.togg} name="selectedState" value={ state }
                            onChange={() => this.setState({ cityState: {state} })}>
    {state}
</DropdownItem>

            ));
            const Cities = this.state.cities.map((city) => (

                <DropdownItem value={ city }
                              onChange={() => this.setState({ cityName: {city} })}>
                    {city}
                </DropdownItem>

            ));
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
                        <Label for={anameLabel}>{anameLabel}</Label>
                        <Input
                            {...anameInputProps}

                            value={this.state.address.addressName}
                            ref={this.input}
                      onChange={this.handleChange}
                        />
                    </FormGroup>

                <FormGroup>
                    <Label for={alineLabel}>{alineLabel}</Label>
                    <Input
                        {...alineInputProps}
                        value={this.state.address.addressLine}
                        onChange={this.handleChange}
                    />
                </FormGroup>

                <FormGroup>
                    <Label for={alandmarkLabel}>{alandmarkLabel}</Label>
                    <Input
                        {...alandmarkInputProps}
                        value={this.state.address.addressLandmark}
                        onChange={this.handleChange}
                    />
                </FormGroup>

                <FormGroup>
                    <Label for={apincodeLabel}>{apincodeLabel}</Label>
                    <Input
                        {...apincodeInputProps}
                        value={this.state.address.addressPincode}
                        onChange={this.handleChange}
                    />
                </FormGroup>


                 <FormGroup>
                    <Label for={acountryLabel}>{acountryLabel}</Label>
        <UncontrolledButtonDropdown className="m-1">
            <DropdownToggle caret  >
                {this.state.address.cityCountry}
            </DropdownToggle>
            <DropdownMenu >
                {Countries}</DropdownMenu>
        </UncontrolledButtonDropdown>
                </FormGroup>


                <FormGroup>
                    <Label for={astateLabel}>{astateLabel}</Label>
                    <UncontrolledButtonDropdown className="m-1">
                        <DropdownToggle caret  >
                            {this.state.address.cityState}
                        </DropdownToggle>
                        <DropdownMenu>
                        {States}
                        </DropdownMenu>
                    </UncontrolledButtonDropdown>

                </FormGroup>



                <FormGroup>
                    <Label for={acityLabel}>{acityLabel}</Label>
                    <UncontrolledButtonDropdown className="m-1">
                        <DropdownToggle caret>{this.state.address.cityName} </DropdownToggle>
                        <DropdownMenu>
                            {Cities}
                        </DropdownMenu>
                    </UncontrolledButtonDropdown>

                </FormGroup>




                <FormGroup>
                    <Label for={isdefaultLabel}>{isdefaultLabel}</Label>
                    <ButtonGroup className="ml-3">
                        <Button
                            {...isdefaultInputProps}
                            value="true"
                            color="primary"
                            onClick={() => this.setState({ isDefaultAddress: "true" })}
                            active={this.state.isDefaultAddress === "true"}
                        >
                            Yes
                        </Button>
                        <Button
                            {...isdefaultInputProps}
                            value="false"
                            color="primary"
                            onClick={() => this.setState({ isDefaultAddress: "false" })}
                            active={this.state.isDefaultAddress === "false"}
                        >
                           No
                        </Button>
                    </ButtonGroup>
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
                    Add Address
                </Button>



                {children}
            </Form>
        );
    }
}



AddressForm.propTypes = {
    showLogo: PropTypes.bool,
    anameLabel: PropTypes.string,
    anameInputProps: PropTypes.object,
    alineLabel: PropTypes.string,
    alineInputProps: PropTypes.object,
    alandmarkLabel: PropTypes.string,
    alandmarkInputProps: PropTypes.object,
    apincodeLabel: PropTypes.string,
    apincodeInputProps: PropTypes.object,
    acityLabel: PropTypes.string,
    acityInputProps: PropTypes.object,
    astateLabel: PropTypes.string,
    astateInputProps: PropTypes.object,
    acountryLabel: PropTypes.string,
    acountryInputProps: PropTypes.object,
    isdefaultLabel: PropTypes.string,
    isdefaultInputProps: PropTypes.object,

    onLogoClick: PropTypes.func,
};

AddressForm.defaultProps = {
    showLogo: true,
    anameLabel: 'Address Name',
    anameInputProps: {
        type: 'text',
        placeholder: 'Enter your Name',
        name:"addressName",
    },
    alineLabel: 'Address line',
    alineInputProps: {
        type: 'text',
        placeholder: 'your Address Line',
        name: "addressLine",
    },
    alandmarkLabel: 'Address Landmark',
    alandmarkInputProps: {
        type: 'text',
        placeholder: 'Enter Landmark',
        name: "addressLandmark",
    },
    apincodeLabel: 'Address Pincode',
    apincodeInputProps: {
        type: 'text',
        placeholder: 'your areas Pincode' ,
        name: "addressPincode",
    },
    acityLabel: 'City',
    acityInputProps: {
        type: 'text',
        placeholder: 'your City',
        name: "cityName",
    },
    astateLabel: 'State',
    astateInputProps: {
        type: 'text',
        placeholder: 'your State',
        name: "cityState",
    },
    acountryLabel: 'Country',
    acountryInputProps: {
        name: "cityCountry",
    },

    isdefaultLabel: 'is this your default Address',
    isdefaultInputProps: {
        name: "isDefaultAddress",
    },
    onLogoClick: () => {},
};

export default AddressForm;
