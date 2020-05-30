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
import Redirect from "react-router/Redirect";

class ProfessionalServForm extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            cityName: "mehsana",
            cityState: "gujarat",
            cityCountry: "india",
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
        this.setState({[event.target.name] : event.target.value })


        console.log("++++++++++++++", event.target.value,"++++++++++++++++++")
        this.setState({address: {...this.state.address, cityCountry : event.target.value }})

    };
    togg = (event) => {
        this.setState({
            show: 3,
        });
        this.setState({
            cities: []
        });

        console.log("++++++++++++++", event.target.value,"++++++++++++++++++")
        this.setState({[event.target.name] : event.target.value })

        console.log("++++++++++++++", event.target.value,"++++++++++++++++++")
        this.setState({address:{ ...this.state.address, cityState : event.target.value }})

    };


    tog = (event) => {

        console.log("++++++++++++++", event.target.value,"++++++++++++++++++")
        this.setState({address:{ ...this.state.address, cityName : event.target.value }})

    };


    handleSubmit = () => {

        const {cityName, cityState,cityCountry} = this.state.address;
        axios
            .post(
                "http://localhost:8081/postcity/"+sessionStorage.getItem("id"),
                {

                    cityName: cityName,
                    cityState: cityState,
                    cityCountry: cityCountry
                }
                // ,
                // { withCredentials: true }
            )
            .then(response => {
                console.log("City Response", response)
                console.log("City Response Data", response.data)
                console.log("City Response Data Status", response.data.status)

                if(response.status)
                    this.props.handleSucces(response.data);
            })
            .catch(error => {
                console.log("Address error", error);
            });



    }

    componentDidMount()

    {

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
        this.setState({ address: { ...this.state.address, [event.target.name] : event.target.value} });
    }


    render() {
        const {
            showLogo,
            acityLabel,
            astateLabel,
            acountryLabel,
            children,
            onLogoClick,
        } = this.props;



        return (
            <Form onSubmit={this.handleSubmit}>
                {/*
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
                */}

                 <FormGroup>
                    <Label for={acountryLabel}>{acountryLabel}</Label>
                    <UncontrolledButtonDropdown className="m-1">
                        <DropdownToggle caret  >
                            {this.state.address.cityCountry}
                        </DropdownToggle>
                        <DropdownMenu >
                            {this.state.countries.map((country) => (
                                <DropdownItem onClick={this.toggle}
                                              name="selectedCountry" value={country}

                                >
                                    {country}
                                </DropdownItem>
                            ))

                            }
                        </DropdownMenu>
                    </UncontrolledButtonDropdown>
                </FormGroup>


                <FormGroup>
                    <Label for={astateLabel}>{astateLabel}</Label>
                    <UncontrolledButtonDropdown className="m-1">
                        <DropdownToggle caret  >
                            {this.state.address.cityState}
                        </DropdownToggle>
                        <DropdownMenu>
                            {this.state.states.map((state) => (


                                <DropdownItem onClick={this.togg} name="selectedState" value={state}
                                >
                                    {state}
                                </DropdownItem>

                            ))
                            }
                        </DropdownMenu>
                    </UncontrolledButtonDropdown>

                </FormGroup>



                <FormGroup>
                    <Label for={acityLabel}>{acityLabel}</Label>
                    <UncontrolledButtonDropdown className="m-1">
                        <DropdownToggle caret>{this.state.address.cityName} </DropdownToggle>
                        <DropdownMenu>
                            {this.state.cities.map((city) => (

                                <DropdownItem value={ city }
                                              onClick={this.tog}>
                                    {city}
                                </DropdownItem>

                            ))
                            }
                        </DropdownMenu>
                    </UncontrolledButtonDropdown>

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
                    Add Service
                </Button>



                {children}
            </Form>
        );
    }
}



ProfessionalServForm.propTypes = {
    showLogo: PropTypes.bool,

    acityLabel: PropTypes.string,
    acityInputProps: PropTypes.object,
    astateLabel: PropTypes.string,
    astateInputProps: PropTypes.object,
    acountryLabel: PropTypes.string,
    acountryInputProps: PropTypes.object,


    onLogoClick: PropTypes.func,
};

ProfessionalServForm.defaultProps = {
    showLogo: true,

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


    onLogoClick: () => {},
};

export default ProfessionalServForm;
