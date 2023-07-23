import React from 'react';

const OrderButton = ({cartItems, customerId, handleOrder}) => {
    const buttonStyle = {
        padding: '12px 24px',
        fontSize: '16px',
        borderRadius: '8px',
        backgroundColor: '#007bff',
        color: '#ffffff',
        cursor: 'pointer',
        marginTop: '10px',
        width: '200px',
    };

    return (
        <div className="order-button">
            <button style={buttonStyle} onClick={() => handleOrder(customerId, cartItems)}>
                Order
            </button>
        </div>
    );
};

export default OrderButton;
