import React from 'react';
import BookDetails from './BookDetails';
import BlogDetails from './BlogDetails';
import CourseDetails from './CourseDetails';

function App() {
  return (
    <div>
      <h1>Blogger App</h1>
      <BookDetails available={true} />
      <BlogDetails published={false} />
      <CourseDetails enrolled={true} />
    </div>
  );
}

export default App;
