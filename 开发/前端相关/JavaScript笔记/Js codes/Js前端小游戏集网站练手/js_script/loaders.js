
// 加载switch
fetch('html_src/switch.html')
    .then(response => response.text())
    .then(data => {
        document.getElementById('switch').innerHTML = data;
    })
    .catch(error => console.error('Error loading content:', error));

// 加载菜单
fetch('html_src/menu.html')
    .then(response => response.text())
    .then(data => {
        document.getElementById('menu').innerHTML = data;
    })
    .catch(error => console.error('Error loading content:', error));