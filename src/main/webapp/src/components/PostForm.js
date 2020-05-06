import React, {Component} from 'react'
import axios from 'axios'

class PostForm extends Component {
	constructor(props) {
		super(props)

		this.state = {
		    customerName: '',
			customerGender: '',
			customerEmail: '',
            password: '',
			mobileNo: ''
        }
	}
	changeHandler = e => {
		this.setState({[e.target.name]: e.target.value});
	}

	submitHandler = e => {
		e.preventDefault()
		console.log(this.state)
		console.log(JSON.stringify(this.state))

		axios
			.post('/postCustomer', this.state)
			.then(response => {
				console.log(response)
			})
			.catch(error => {
				console.log(error)
			})
	}

	render() {
		const { customerName, customerGender, customerEmail, password, mobileNo } = this.state
		return (
			<div>
				<form onSubmit={this.submitHandler} >
					    <div>
                            <input
                                type="text"
                                name="customerName"
                                value={customerName}
                                onChange={this.changeHandler}
                            />
                        </div>
                        <div>
                            <input
                                type="text"
                                name="customerGender"
                                value={customerGender}
                                onChange={this.changeHandler}
                            />
                        </div>
                        <div>
                            <input
                                type="text"
                                name="customerEmail"
                                value={customerEmail}
                                onChange={this.changeHandler}
                            />
                        </div>


                        <div>
                            <input
                                type="text"
                                name="password"
                                value={password}
                                onChange={this.changeHandler}
                            />
                        </div>
                        <div>
                            <input
                                type="text"
                                name="mobileNo"
                                value={mobileNo}
                                onChange={this.changeHandler}
                            />
                        </div>

					<button type="submit">Submit</button>
				</form>
			</div>
		)
	}
}

export default PostForm
