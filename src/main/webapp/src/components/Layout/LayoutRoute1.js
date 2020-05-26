import React from 'react';
import {Route} from 'react-router-dom';
import {MainLayout1} from "./index";

const LayoutRoute1 = ({ component: Component, layout: Layout, ...rest }) => (
  <Route
    {...rest}
    render={props => (
      <MainLayout1>
        <Component {...props} />
      </MainLayout1>
    )}
  />
);

export default LayoutRoute1;
