import React from 'react';

const ShoppingCart = ({cartItems, onAdd}) => {
    const [totalPrice, setTotalPrice] = React.useState(0);

    React.useEffect(() => {
        const total = cartItems.reduce((total, item) => total + Number(item.price), 0);
        setTotalPrice(total);
    }, [cartItems]);

    if (!cartItems || cartItems.length === 0) {
        return <p style={{fontSize: '24px'}}>Your cart is empty.</p>;
    }

    return (
        <div className="cart" style={{backgroundColor: '#f8f9fa', padding: '20px'}}>
            <h2>Shopping Cart</h2>
            <div style={{display: 'flex', gap: '20px', flexWrap: 'wrap'}}>
                {cartItems.map((item, index) => (
                    <div
                        key={item.bookId}
                        className="cart-item"
                        style={{
                            border: `1px solid ${item.bookColor}`,
                            backgroundColor: `${item.bookColor}`,
                            padding: '10px',
                            flex: '1 0 200px',
                        }}
                    >
                        <p>{item.title}</p>
                        <p>Quantity: {item.quantity}</p>
                        <p>Price: {item.price} won</p>
                        <button onClick={() => onAdd(item)}>Add to cart</button>
                    </div>
                ))}
            </div>
            <p style={{fontSize: '20px', fontWeight: 'bold'}}>Total Price: {totalPrice} won</p>
        </div>
    );
};

export default ShoppingCart;
