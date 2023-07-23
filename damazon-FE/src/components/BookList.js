import React from 'react';
import Book from './Book';

const BookList = ({books, addToCart}) => {
    return (
        <div className="book-list">
            {books.map((book) => (
                <Book key={book.id} book={book} addToCart={addToCart}/>
            ))}
        </div>
    );
};

export default BookList;