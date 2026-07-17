import React from 'react';

// Technique 1: if/else statement
function BookDetails({ available }) {
  if (available) {
    return <p>Book Details: "Clean Code" by Robert C. Martin - In Stock</p>;
  } else {
    return <p>Book Details: Currently Out of Stock</p>;
  }
}

export default BookDetails;
