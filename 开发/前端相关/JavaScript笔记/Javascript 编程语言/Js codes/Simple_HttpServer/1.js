const username = 'admin';
const password = 'password';
const url = 'http://localhost:8080'; // 确保这个 URL 与后端服务器的地址和端口匹配

fetch(url, {
  method: 'POST',
  body: `${username}=${password}`,
  headers: {
    'Content-Type': 'text/plain'
  }
})
.then(response => response.text())
.then(data => {
  console.log('Response from server:', data);
})
.catch(error => {
  console.error('Error:', error);
});
