import React from 'react';
import Post from './Post';

class Posts extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      error: null
    };
  }

  loadPosts = () => {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then((response) => response.json())
      .then((data) => {
        const posts = data
          .slice(0, 10)
          .map((p) => new Post(p.userId, p.id, p.title, p.body));
        this.setState({ posts });
      })
      .catch((err) => {
        throw err;
      });
  };

  componentDidMount() {
    this.loadPosts();
  }

  componentDidCatch(error, info) {
    this.setState({ error: error.message });
    alert(`Something went wrong while loading posts: ${error.message}`);
  }

  render() {
    if (this.state.error) {
      return <p>Error loading posts: {this.state.error}</p>;
    }
    return (
      <div>
        <h1>Blog Posts</h1>
        {this.state.posts.map((post) => (
          <div key={post.id}>
            <h3>{post.title}</h3>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;
