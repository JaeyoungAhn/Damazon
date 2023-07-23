import React, {useEffect, useState} from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import "./App.css";
import Header from "./components/Header";
import BookList from "./components/BookList";
import ShoppingCart from "./components/ShoppingCart";
import OrderButton from "./components/OrderButton";
import useCart from "./components/useCart";
import placeOrder from "./components/orderService";
import UserSelector from "./components/UserSelector";
import axios from "axios";
import Management from "./components/Management";
import OrderList from "./components/OrderList";

const DefaultView = ({user, setUser, cart, addToCart, clearCart, orderPlaced, setOrderPlaced, books}) => (
    <>
        <UserSelector onUserSelect={setUser}/>
        <ShoppingCart cartItems={cart}/>
        {orderPlaced ? (
            <p>Order placed successfully!</p>
        ) : (
            user &&
            cart.length > 0 && (
                <OrderButton
                    cartItems={cart}
                    customerId={user.id}
                    handleOrder={() =>
                        user &&
                        placeOrder(user.id, cart).then(() => {
                            clearCart();
                            setOrderPlaced(true);

                            setTimeout(() => {
                                setUser(null);
                                setOrderPlaced(false);
                            }, 3000);
                        })
                    }
                />
            )
        )}
        <BookList books={books} addToCart={addToCart}/>
    </>
);

const App = () => {
    const [books, setBooks] = useState([]);
    const [user, setUser] = useState();
    const {cart, addToCart, clearCart} = useCart([]);
    const [orderPlaced, setOrderPlaced] = useState(false);

    useEffect(() => {
        axios
            .get("http://localhost:8080/api/books")
            .then((response) => setBooks(response.data))
            .catch((error) => console.error("Error fetching books:", error));
    }, []);

    return (
        <div className="app">
            <Router>
                <Header/>
                <Routes>
                    <Route path="/management" element={<Management/>}/>
                    <Route path="/order-list" element={<OrderList/>}/>
                    <Route path="*"
                           element={<DefaultView user={user} setUser={setUser} cart={cart} addToCart={addToCart}
                                                 clearCart={clearCart} orderPlaced={orderPlaced}
                                                 setOrderPlaced={setOrderPlaced} books={books}/>}/>
                </Routes>
            </Router>
        </div>
    );
};

export default App;