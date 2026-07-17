import React from 'react';

// Technique 2: ternary operator
function BlogDetails({ published }) {
  return (
    <p>
      Blog Details: {published ? '"Understanding React Hooks" is live' : 'Draft not yet published'}
    </p>
  );
}

export default BlogDetails;
