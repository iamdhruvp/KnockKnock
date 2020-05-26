import React from 'react';

import {Nav, Navbar, NavItem} from 'reactstrap';

import SourceLink from 'components/SourceLink';

const Footer1 = () => {
  return (
    <Navbar>
      <Nav navbar>
        <NavItem>
          2020 Knock Knock, source on <SourceLink>Github</SourceLink>
        </NavItem>
      </Nav>
    </Navbar>
  );
};

export default Footer1;
