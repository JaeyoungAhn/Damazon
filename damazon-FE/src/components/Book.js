import React, {useMemo} from 'react';

const Book = ({book, addToCart}) => {
    const getRandomColor = () => {
        const letters = '0123456789ABCDEF';
        let color = '#';
        for (let i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color + '60';
    };

    const bookColor = useMemo(() => getRandomColor(), []);

    const handleAddToCart = () => {
        addToCart(book, bookColor);
    };

    return (
        <div className="book" style={{border: `2px solid ${bookColor}`, backgroundColor: bookColor}}>
            <h2>{book.title}</h2>
            <p>By {book.author}</p>
            <p>({book.category})</p>
            <p>Price: {book.price} won</p>
            <button onClick={handleAddToCart}>Add to Cart</button>
        </div>
    );
};

export default Book;
