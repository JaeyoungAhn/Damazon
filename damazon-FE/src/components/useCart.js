import {useState} from 'react';

const useCart = () => {
    const [cart, setCart] = useState([]);
    const [totalPrice, setTotalPrice] = useState(0);

    const addToCart = (book, bookColor) => {
        const existingCartItem = cart.find((item) => item.bookId === book.id);
        if (existingCartItem) {
            setCart((prevCart) =>
                prevCart.map((item) =>
                    item.bookId === book.id ? {
                        ...item,
                        quantity: item.quantity + 1,
                        price: (item.quantity + 1) * book.price
                    } : item
                )
            );
        } else {
            setCart((prevCart) => [
                ...prevCart,
                {bookId: book.id, title: book.title, quantity: 1, price: book.price, bookColor: bookColor},
            ]);
        }


        setTotalPrice(prevTotalPrice => prevTotalPrice + book.price);
    };

    const clearCart = () => {
        setCart([]);
        setTotalPrice(0);
    };

    return {cart, addToCart, clearCart, totalPrice};
};

export default useCart;
