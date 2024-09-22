const url = 'http://127.0.0.1:8080/user/login'; // 确保这个 URL 与后端服务器的地址和端口匹配
//const username = 'admin';
//const password = 'password';

document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 阻止默认的表单提交行为

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const errorMessage = document.getElementById('error-message');
    
    let user = {'username': username, 'password': password};
    let user_json = JSON.stringify(user);

    fetch(url, {
        method: 'POST',
        body: user_json,
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        console.log('Response from server:', data);

        if (data.message === "Login successful")
        {
            errorMessage.style.display = 'none';
            // 存储用户名
            localStorage.setItem('myname', username);
            // 存储响应数据到localStorage
            localStorage.setItem('userData', JSON.stringify(data));
            // 跳转到 chat.html
            location.assign("chat2.html");
        }
        else
        {
            errorMessage.style.display = 'block';
        }
    })
    .catch(error => {
        console.error('Error:', error);
        errorMessage.style.display = 'block';
    });
});
