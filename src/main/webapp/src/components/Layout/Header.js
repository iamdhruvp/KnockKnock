import Avatar from 'components/Avatar';
import {UserCard} from 'components/Card';
import Notifications from 'components/Notifications';
import SearchInput from 'components/SearchInput';
import {notificationsData} from 'demos/header';
import withBadge from 'hocs/withBadge';
import React from 'react';
import {
  MdClearAll,
  MdExitToApp,
  MdHelp,
  MdInsertChart,
  MdMessage,
  MdNotificationsActive,
  MdNotificationsNone,
  MdPersonPin,
  MdSettingsApplications,
} from 'react-icons/md';
import {Button, ListGroup, ListGroupItem, Nav, Navbar, NavItem, NavLink, Popover, PopoverBody,} from 'reactstrap';
import bn from 'utils/bemnames';
import axios from "axios";
import Redirect from "react-router/Redirect";

const bem = bn.create('header');

const MdNotificationsActiveWithBadge = withBadge({
  size: 'md',
  color: 'primary',
  style: {
    top: -10,
    right: -10,
    display: 'inline-flex',
    justifyContent: 'center',
    alignItems: 'center',
  },
  children: <small>5</small>,
})(MdNotificationsActive);

class Header extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      isOpenNotificationPopover: false,
      isNotificationConfirmed: false,
      isOpenUserCardPopover: false,
      customer: []
    };

    this.handleLogoutClick = this.handleLogoutClick.bind(this);
  }


  handleLogoutClick() {
    sessionStorage.removeItem("id");
    sessionStorage.removeItem("user");
    sessionStorage.removeItem("role");
    window.open("/");
  }
  componentDidMount() {

    {
      axios.get(process.env.REACT_APP_API_URL +`/getcustomer/`+sessionStorage.getItem("id"))
          .then(res => {
            console.log(res.data)
            this.setState({customer: res.data});
          })

    }
  }

  toggleNotificationPopover = () => {
    this.setState({
      isOpenNotificationPopover: !this.state.isOpenNotificationPopover,
    });

    if (!this.state.isNotificationConfirmed) {
      this.setState({ isNotificationConfirmed: true });
    }
  };

  toggleUserCardPopover = () => {
    this.setState({
      isOpenUserCardPopover: !this.state.isOpenUserCardPopover,
    });
  };

  handleSidebarControlButton = event => {
    event.preventDefault();
    event.stopPropagation();

    document.querySelector('.cr-sidebar').classList.toggle('cr-sidebar--open');
  };

  render() {
    const { isNotificationConfirmed } = this.state;

    return (
        <Navbar light expand className={bem.b('bg-white')}>
          <Nav navbar className="const {Header}mr-2">
            <Button outline onClick={this.handleSidebarControlButton}>
              <MdClearAll size={25} />
            </Button>
          </Nav>
          <Nav navbar>
            <SearchInput />
          </Nav>

          <Nav navbar className={bem.e('nav-right')}>
            <NavItem className="d-inline-flex">
              <NavLink id="Popover1" className="position-relative">
                {isNotificationConfirmed ? (
                    <MdNotificationsNone
                        size={25}
                        className="text-secondary can-click"
                        onClick={this.toggleNotificationPopover}
                    />
                ) : (
                    <MdNotificationsActiveWithBadge
                        size={25}
                        className="text-secondary can-click animated swing infinite"
                        onClick={this.toggleNotificationPopover}
                    />
                )}
              </NavLink>
              <Popover
                  placement="bottom"
                  isOpen={this.state.isOpenNotificationPopover}
                  toggle={this.toggleNotificationPopover}
                  target="Popover1"
              >
                <PopoverBody>
                  <Notifications notificationsData={notificationsData} />
                </PopoverBody>
              </Popover>
            </NavItem>

            <NavItem>
              <NavLink id="Popover2">
                <Avatar
                    onClick={this.toggleUserCardPopover}
                    className="can-click"
                />
              </NavLink>
              <Popover
                  placement="bottom-end"
                  isOpen={this.state.isOpenUserCardPopover}
                  toggle={this.toggleUserCardPopover}
                  target="Popover2"
                  className="p-0 border-0"
                  style={{ minWidth: 250 }}
              >
                <PopoverBody className="p-0 border-light">
                  <UserCard
                      title={this.state.customer.customerName}
                      subtitle={this.state.customer.customerEmail}
                      subtitle={this.state.customer.mobileNo}
                      className="border-light"
                  >
                    <ListGroup flush>
                      <ListGroupItem tag="button" action className="border-light">
                        <MdPersonPin /> Profile
                      </ListGroupItem>
                      <ListGroupItem tag="button" action className="border-light">
                        <MdInsertChart /> Stats
                      </ListGroupItem>

                      <ListGroupItem tag="button" action className="border-light"
                                     onClick={() => this.handleLogoutClick()}>
                        <MdExitToApp /> Signout
                      </ListGroupItem>
                    </ListGroup>
                  </UserCard>
                </PopoverBody>
              </Popover>
            </NavItem>
          </Nav>
        </Navbar>
    );
  }
}

export default Header;
