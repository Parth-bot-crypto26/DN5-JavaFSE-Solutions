import axios from 'axios';
import GitClient from './GitClient';

jest.mock('axios');

describe('Git Client Tests', () => {
  test('should return repository names for techiesyed', async () => {
    const mockData = {
      data: [
        { name: 'digital-nurture-java-fse' },
        { name: 'react-samples' },
        { name: 'spring-boot-microservices' }
      ]
    };
    axios.get.mockResolvedValue(mockData);

    const gitClient = new GitClient();
    const repoNames = await gitClient.getRepositories('techiesyed');

    expect(axios.get).toHaveBeenCalledWith('https://api.github.com/users/techiesyed/repos');
    expect(repoNames).toEqual(['digital-nurture-java-fse', 'react-samples', 'spring-boot-microservices']);
  });
});
