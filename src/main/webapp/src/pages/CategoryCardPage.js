import React from 'react';
import Page from 'components/Page';
import {Button, Card, CardBody, CardColumns, CardFooter, CardImg, CardSubtitle, CardText, CardTitle} from 'reactstrap';
import bg11Image from 'assets/img/bg/background_1920-11.jpg';
import axios from "axios";

class CategoryCardPage extends React.Component{
    state = {
        first:"Select",
        second:"Select",
        show: 1,
        categories:[],
        selectedCategory:0,
        subcategories:[],
        selectedSubCategory:0

    };
    
    toggle = (event) => {
        this.setState({
            show: this.state.show + 1,
        });
        console.log("++++++++++++++", event.target.value,"++++++++++++++++++")
        this.setState({ [event.target.name] : event.target.value })
    };

    componentDidMount() {
        axios.get(`http://localhost:8081/getCategory`)
            .then(res => {
                console.log(res.data)
                this.setState({ categories : res.data });
            })
    }
    componentDidUpdate() {

        { this.state.show === 2 && this.state.subcategories.length === 0 &&
            axios.get(`http://localhost:8081/getSubCategory/` + this.state.selectedCategory)
                .then(res => {
                    console.log(res.data)
                    this.setState({subcategories: res.data});
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
        const Categories = this.state.categories.map((category, i) => (
            <Card>
                <CardImg top width="100%" src={bg11Image} alt="Card image cap" />
                <CardBody>
                    <CardTitle>{category.serviceCategoryName}</CardTitle>
                    <CardSubtitle>Category Id : { category.serviceCategoryId }</CardSubtitle>
                    <CardText>Array Index : { i }</CardText>
                    <Button onClick={this.toggle} name="selectedCategory" value={ category.serviceCategoryId }>Button</Button>
                </CardBody>
            </Card>
        ));
        const SubCategories = this.state.subcategories.map((subcategory, i) => (
            <Card>
                <CardImg top width="100%" src={bg11Image} alt="Card image cap" />
                <CardBody>
                    <CardTitle>{ subcategory.serviceSubCategoryName }</CardTitle>
                    <CardSubtitle>SubCategory Id : { subcategory.serviceSubCategoryId }</CardSubtitle>
                    <CardText>Desc : { subcategory.serviceSubCategoryDesc }</CardText>
                </CardBody>
                <CardFooter className="text-muted">
                    <Button color="primary"  active={true} onClick={this.toggle} block>{this.state.first}</Button>

                </CardFooter>
            </Card>
        ));
        return (
            <div>
                {this.state.show === 1 &&
                (<Page title="Category" breadcrumbs={[{ name: 'Category', active: true }]}>
                    <CardColumns>{ Categories }</CardColumns>
                </Page>)
                }
                { this.state.show === 2 && (
                <Page title="SubCategory" breadcrumbs={[{ name: 'Category / SubCategory', active: true }]}>
                        <CardColumns>{ SubCategories }</CardColumns>
                    </Page>
                )}
            </div>
        );
    }
}

export default CategoryCardPage;
