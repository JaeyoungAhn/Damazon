import React, {useEffect, useState} from 'react';
import axios from 'axios';
import '../css/OrderList.css';

const OrderList = () => {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        axios
            .get('http://localhost:8080/api/bookorders')
            .then((response) => {
                setOrders(response.data);
            })
            .catch((error) => {
                console.error('Error fetching orders:', error);
            });
    }, []);

    const getRandomColor = () => {
        const letters = '0123456789ABCDEF';
        let color = '#';
        for (let i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color + '20';
    };

    return (
        <div className="orders-container">
            {orders.map((order, index) => {
                const totalPrice = order.items.reduce((total, item) => total + item.price * item.quantity, 0);

                return (
                    <div
                        className="order"
                        key={index}
                        style={{
                            border: `1px solid`,
                            backgroundColor: `${getRandomColor()}`,
                        }}
                    >
                        <h2 className="order-header">Customer : {order.customerName}</h2>
                        <h3 className="order-status">Order Status: {order.orderStatus}</h3>
                        <h2 className="order-header">Date : {order.createdAt}</h2>
                        <ul className="order-items">
                            {order.items.map((item, idx) => (
                                <li key={idx} className="order-item">
                                    Title : {item.title}, Quantity: {item.quantity},
                                    Price: {item.price * item.quantity} won
                                </li>
                            ))}
                        </ul>
                        <p className="total-price">Total Price: {totalPrice} won</p>
                    </div>
                );
            })}
        </div>
    );
};

export default OrderList;