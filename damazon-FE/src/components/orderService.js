import axios from 'axios';

const placeOrder = (customerId, cartItems) => {
    const orderItems = cartItems.map((item) => ({
        bookId: item.bookId,
        quantity: item.quantity,
    }));

    return axios.post('http://localhost:8080/api/bookorders', {
        customerId,
        status: 'PENDING',
        items: orderItems,
    });
};

export default placeOrder;