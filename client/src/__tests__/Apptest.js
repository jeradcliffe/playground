import React from 'react';
import App from '../components/App/App';
import {shallow} from 'enzyme';

test('application loads', () => {
    const tree = shallow(<App/>);
    expect(tree).toHaveLength(1);
});