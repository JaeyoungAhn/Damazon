import React, {useState} from "react";
import axios from "axios";

const Management = () => {
    const [customerInput, setCustomerInput] = useState({
        name: "",
        address: "",
        email: "",
    });
    const [bookInput, setBookInput] = useState({
        title: "",
        author: "",
        price: "",
        categoryId: "",
    });
    const [deleteCustomerId, setDeleteCustomerId] = useState("");
    const [deleteBookId, setDeleteBookId] = useState("");
    const [updateCustomerId, setUpdateCustomerId] = useState("");
    const [updateCustomerInput, setUpdateCustomerInput] = useState({
        name: "",
        address: "",
        email: "",
    });
    const [updateBookId, setUpdateBookId] = useState("");
    const [updateBookInput, setUpdateBookInput] = useState({
        title: "",
        author: "",
        price: "",
        categoryId: "",
    });
    const [fetchBookId, setFetchBookId] = useState("");
    const [book, setBook] = useState();
    const [fetchCustomerId, setFetchCustomerId] = useState("");
    const [customer, setCustomer] = useState();
    const [createdCustomer, setCreatedCustomer] = useState(null);
    const [createdBook, setCreatedBook] = useState(null);
    const [deletedCustomer, setDeletedCustomer] = useState(null);
    const [deletedBook, setDeletedBook] = useState(null);
    const [updatedCustomer, setUpdatedCustomer] = useState(null);
    const [updatedBook, setUpdatedBook] = useState(null);

    const handleInputChange = (event, setInput, field) => {
        setInput((prev) => ({...prev, [field]: event.target.value}));
    };

    const createCustomer = () => {
        axios
            .post("http://localhost:8080/api/customers", customerInput)
            .then((res) => setCreatedCustomer(res.data))
            .catch((err) => console.error(err));
    };

    const createBook = () => {
        axios
            .post("http://localhost:8080/api/books", bookInput)
            .then((res) => setCreatedBook(res.data))
            .catch((err) => console.error(err));
    };

    const deleteCustomer = () => {
        axios
            .delete(`http://localhost:8080/api/customers/${deleteCustomerId}`)
            .then((res) => setDeletedCustomer(res.data))
            .catch((err) => console.error(err));
    };

    const deleteBook = () => {
        axios
            .delete(`http://localhost:8080/api/books/${deleteBookId}`)
            .then((res) => setDeletedBook(res.data))
            .catch((err) => console.error(err));
    };

    const updateCustomer = () => {
        axios
            .put(
                `http://localhost:8080/api/customers/${updateCustomerId}`,
                updateCustomerInput
            )
            .then((res) => setUpdatedCustomer(res.data))
            .catch((err) => console.error(err));
    };

    const updateBook = () => {
        axios
            .put(`http://localhost:8080/api/books/${updateBookId}`, updateBookInput)
            .then((res) => setUpdatedBook(res.data))
            .catch((err) => console.error(err));
    };

    const fetchBook = () => {
        axios
            .get(`http://localhost:8080/api/books/${fetchBookId}`)
            .then((response) => {
                setBook(response.data);
            })
            .catch((error) => {
                console.error("Error fetching book:", error);
            });
    };

    const fetchCustomer = () => {
        axios
            .get(`http://localhost:8080/api/customers/${fetchCustomerId}`)
            .then((response) => {
                setCustomer(response.data);
            })
            .catch((error) => {
                console.error("Error fetching customer:", error);
            });
    };

    return (
        <div>
            <h1>Management Component</h1>

            <div>
                <h2>Create Customer</h2>
                <input
                    type="text"
                    placeholder="Name"
                    onChange={(e) => handleInputChange(e, setCustomerInput, "name")}
                />
                <input
                    type="text"
                    placeholder="Address"
                    onChange={(e) => handleInputChange(e, setCustomerInput, "address")}
                />
                <input
                    type="text"
                    placeholder="Email"
                    onChange={(e) => handleInputChange(e, setCustomerInput, "email")}
                />
                <button onClick={createCustomer}>Create Customer</button>
            </div>

            {createdCustomer && (
                <div>
                    <h3>Created Customer:</h3>
                    <p>{createdCustomer.name}</p>
                    <p>{createdCustomer.address}</p>
                    <p>{createdCustomer.email}</p>
                    <p>{createdCustomer.updatedAt}</p>
                </div>
            )}

            <div>
                <h2>Create Book</h2>
                <input
                    type="text"
                    placeholder="Title"
                    onChange={(e) => handleInputChange(e, setBookInput, "title")}
                />
                <input
                    type="text"
                    placeholder="Author"
                    onChange={(e) => handleInputChange(e, setBookInput, "author")}
                />
                <input
                    type="number"
                    placeholder="Price"
                    onChange={(e) => handleInputChange(e, setBookInput, "price")}
                />
                <input
                    type="number"
                    placeholder="Category Id"
                    onChange={(e) => handleInputChange(e, setBookInput, "categoryId")}
                />
                <button onClick={createBook}>Create Book</button>
            </div>

            {createdBook && (
                <div>
                    <h3>Created Book:</h3>
                    <p>{createdBook.title}</p>
                    <p>{createdBook.author}</p>
                    <p>{createdBook.price}</p>
                    <p>{createdBook.category}</p>
                    <p>{createdBook.updatedAt}</p>
                </div>
            )}

            <div>
                <h2>Delete Customer</h2>
                <input
                    type="number"
                    placeholder="Customer ID"
                    onChange={(e) => setDeleteCustomerId(e.target.value)}
                />
                <button onClick={deleteCustomer}>Delete Customer</button>
            </div>

            {deletedCustomer && (
                <div>
                    <h3>Deleted Customer:</h3>
                    <p>{deletedCustomer.name}</p>
                    <p>{deletedCustomer.address}</p>
                    <p>{deletedCustomer.email}</p>
                    <p>{deletedCustomer.deletedAt}</p>
                </div>
            )}

            <div>
                <h2>Delete Book</h2>
                <input
                    type="number"
                    placeholder="Book ID"
                    onChange={(e) => setDeleteBookId(e.target.value)}
                />
                <button onClick={deleteBook}>Delete Book</button>
            </div>

            {deletedBook && (
                <div>
                    <h3>Deleted Book:</h3>
                    <p>{deletedBook.title}</p>
                    <p>{deletedBook.author}</p>
                    <p>{deletedBook.price}</p>
                    <p>{deletedBook.categoryId}</p>
                    <p>{deletedBook.deletedAt}</p>
                </div>
            )}

            <div>
                <h2>Update Customer</h2>
                <input
                    type="number"
                    placeholder="Customer ID"
                    onChange={(e) => setUpdateCustomerId(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Name"
                    onChange={(e) => handleInputChange(e, setUpdateCustomerInput, "name")}
                />
                <input
                    type="text"
                    placeholder="Address"
                    onChange={(e) =>
                        handleInputChange(e, setUpdateCustomerInput, "address")
                    }
                />
                <input
                    type="text"
                    placeholder="Email"
                    onChange={(e) =>
                        handleInputChange(e, setUpdateCustomerInput, "email")
                    }
                />
                <button onClick={updateCustomer}>Update Customer</button>
            </div>

            {updatedCustomer && (
                <div>
                    <h3>Updated Customer:</h3>
                    <p>{updatedCustomer.name}</p>
                    <p>{updatedCustomer.address}</p>
                    <p>{updatedCustomer.email}</p>
                    <p>{updatedCustomer.updatedAt}</p>
                </div>
            )}

            <div>
                <h2>Update Book</h2>
                <input
                    type="number"
                    placeholder="Book ID"
                    onChange={(e) => setUpdateBookId(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Title"
                    onChange={(e) => handleInputChange(e, setUpdateBookInput, "title")}
                />
                <input
                    type="text"
                    placeholder="Author"
                    onChange={(e) => handleInputChange(e, setUpdateBookInput, "author")}
                />
                <input
                    type="number"
                    placeholder="Price"
                    onChange={(e) => handleInputChange(e, setUpdateBookInput, "price")}
                />
                <input
                    type="number"
                    placeholder="Category Id"
                    onChange={(e) =>
                        handleInputChange(e, setUpdateBookInput, "categoryId")
                    }
                />
                <button onClick={updateBook}>Update Book</button>
            </div>

            {updatedBook && (
                <div>
                    <h3>Updated Book:</h3>
                    <p>{updatedBook.title}</p>
                    <p>{updatedBook.author}</p>
                    <p>{updatedBook.price}</p>
                    <p>{updatedBook.category}</p>
                    <p>{updatedBook.updatedAt}</p>
                </div>
            )}

            <div>
                <h2>Fetch Book</h2>
                <input
                    type="number"
                    placeholder="Book ID"
                    onChange={(e) => setFetchBookId(e.target.value)}
                />
                <button onClick={fetchBook}>Fetch Book</button>
            </div>

            {book && (
                <div>
                    <h3>Fetched Book:</h3>
                    <p>{book.title}</p>
                    <p>{book.author}</p>
                    <p>{book.price}</p>
                    <p>{book.categoryId}</p>
                </div>
            )}

            <div>
                <h2>Fetch Customer</h2>
                <input
                    type="number"
                    placeholder="Customer ID"
                    onChange={(e) => setFetchCustomerId(e.target.value)}
                />
                <button onClick={fetchCustomer}>Fetch Customer</button>
            </div>

            {customer && (
                <div>
                    <h3>Fetched Customer:</h3>
                    <p>{customer.name}</p>
                    <p>{customer.address}</p>
                    <p>{customer.email}</p>
                </div>
            )}
        </div>
    );
};

export default Management;
