document.addEventListener('DOMContentLoaded', () => {
    // 从 localStorage 获取初始数据
    const myname = localStorage.getItem('myname');
    const storedData = localStorage.getItem('userData');
    const data = JSON.parse(storedData);
    const socket = new WebSocket(`ws://127.0.0.1:8080/ws?username=${myname}`); // 连接WebSocket服务器
    let isGroup = 0;

    // 检查数据是否正确
    if (!data || !data.friends || !data.friend_states || !data.groups) {
        console.error('数据格式错误或数据丢失');
        return;
    }

    // WebSocket连接建立后的处理
    socket.onopen = function(event) {
        console.log('WebSocket连接已建立');
    };
    
    // WebSocket连接关闭后的处理
    socket.onclose = function(event) {
        console.log('WebSocket连接已关闭');
    };

    // 获取 DOM 元素
    const friendsUl = document.getElementById('friends');
    const groupsUl = document.getElementById('groups');
    const chatWith = document.getElementById('chatWith');
    const chatMessages = document.getElementById('chatMessages');
    const messageInput = document.getElementById('messageInput');
    const sendButton = document.getElementById('sendButton');
    const userName = document.getElementById('username');

    // 渲染好友列表
    function renderFriends() {
        friendsUl.innerHTML = '';
        data.friends.forEach((friend, index) => {
            if (index < data.friend_states.length) { // 确保索引有效
                const li = document.createElement('li');
                li.textContent = friend + (data.friend_states[index] == '1' ? ' (在线)' : ' (离线)');
                if (data.friend_states[index] == '1') {
                    li.classList.add('online');
                }
                li.addEventListener('click', () => {
                    openChat(friend);
                    isGroup = 0;
                });
                friendsUl.appendChild(li);
            }
        });
    }

    // 渲染群组列表
    function renderGroups() {
        groupsUl.innerHTML = '';
        data.groups.forEach(group => {
            const li = document.createElement('li');
            li.textContent = group;
            li.addEventListener('click', () =>{
                openChat(group);
                isGroup = 1;
            });
            groupsUl.appendChild(li);
        });
    }

    // 打开聊天
    function openChat(name) {
        chatWith.textContent = `和 ${name} 聊天`;
        renderChatHistory(myname + name);
    }

    // 渲染聊天历史记录
    function renderChatHistory(chatPartner) {
        chatMessages.innerHTML = ''; // 清空之前的消息
        const chatHistory = JSON.parse(localStorage.getItem(chatPartner)) || [];
        chatHistory.forEach(message => {
            const messageDiv = createMessageElement(message.sender, message.message, message.type);
            chatMessages.appendChild(messageDiv);
        });
    }

    // WebSocket接收到消息后的处理
    socket.onmessage = function(event) {
        const data_json = event.data;
        if (data_json) {
            const parsedMessage = JSON.parse(data_json); // 解析JSON字符串
            const targetUser = parsedMessage.sender;
            const message = parsedMessage.messages; // 存储的消息

            if (targetUser == chatWith.textContent.slice(2, -3)) {
                sendMessage('Ta', messages, 'friend');
            }

            let historyName = myname + targetUser;
            saveMessageToLocalStorage(historyName, 'Ta', messages, 'friend'); // 保存消息到本地存储
        }
    };

    // 发送消息
    function sendMessageAndClearInput() {
        const message = messageInput.value.trim();
        if (message) {
            const targetUser = chatWith.textContent.slice(2, -3);
            sendMessage('我', message, 'user');

            let historyName = myname + targetUser;
            saveMessageToLocalStorage(historyName, '我', message, 'user'); // 保存消息到本地存储
            messageInput.value = ''; // 清空输入框内容
            socket.send(JSON.stringify({"sender":myname,"receiver": targetUser,"isgroup":isGroup, "message": message})); // 发送消息到服务器
        }
    }

    // 发送消息并将消息显示在聊天窗口
    function sendMessage(sender, message, messageType) {
        const messageDiv = createMessageElement(sender, message, messageType);
        chatMessages.appendChild(messageDiv);
        chatMessages.scrollTop = chatMessages.scrollHeight; // 滚动到底部
    }

    // 创建消息元素
    function createMessageElement(sender, message, messageType) {
        const messageDiv = document.createElement('div');
        messageDiv.classList.add('message', messageType);
        messageDiv.innerHTML = `<span class="sender">${sender}:</span> ${message}`;
        return messageDiv;
    }

    // 将消息保存到本地存储
    function saveMessageToLocalStorage(chatPartner, sender, message, messageType) {
        const chatHistory = JSON.parse(localStorage.getItem(chatPartner)) || [];
        const newMessage = { sender, message, type: messageType };
        chatHistory.push(newMessage);
        localStorage.setItem(chatPartner, JSON.stringify(chatHistory));
    }

    // 初始渲染
    userName.textContent = myname;
    renderFriends();
    renderGroups();

    // 发送按钮点击事件
    sendButton.addEventListener('click', () => {
        sendMessageAndClearInput();
    });

    // 输入框按下Enter键事件
    messageInput.addEventListener('keydown', (event) => {
        if (event.keyCode === 13) { // Enter键的keyCode是13
            sendMessageAndClearInput();
        }
    });
});
