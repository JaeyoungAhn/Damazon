import React from 'react';
import {Link} from 'react-router-dom';
import '../css/Header.css';

const Header = () => {
    return (
        <header className="header">
            <div className="header-container">
                <div className="logo">Damazon</div>
                <div className="nav-links">
                    <Link to="/">Home</Link>
                    <Link to="/management">Management</Link>
                    <Link to="/order-list">Order List</Link>
                </div>
            </div>
        </header>
    );
};

export default Header;
