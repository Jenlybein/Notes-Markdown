document.addEventListener('DOMContentLoaded', () => {
    // 从 localStorage 获取初始数据
    const myname = localStorage.getItem('myname');
    const storedData = localStorage.getItem('userData');
    const data = JSON.parse(storedData);
    const socket = new WebSocket(`ws://127.0.0.1:8080/ws?username=${myname}`); // 连接 WebSocket 服务器
    let isGroup = "0";

    // 检查数据是否正确
    if (!data || !data.friends || !data.friend_states || !data.groups) {
        console.error('数据格式错误或数据丢失');
        return;
    }

    // WebSocket 事件处理
    socket.onopen = () => console.log('WebSocket连接已建立');
    socket.onclose = () => console.log('WebSocket连接已关闭');
    socket.onmessage = handleIncomingMessage;

    // 获取 DOM 元素
    const friendsUl = document.getElementById('friends');
    const groupsUl = document.getElementById('groups');
    const chatWith = document.getElementById('chatWith');
    const chatMessages = document.getElementById('chatMessages');
    const messageInput = document.getElementById('messageInput');
    const sendButton = document.getElementById('sendButton');
    const userName = document.getElementById('username');
    const logOut = document.getElementById('logoutButton')
    // 初始渲染
    userName.textContent = myname;
    renderFriends();
    renderGroups();

    // 事件监听
    logOut.addEventListener('click', ()=>{location.assign("login.html")});
    sendButton.addEventListener('click', sendMessageAndClearInput);
    messageInput.addEventListener('keydown', (event) => {
        if (event.key === 'Enter') sendMessageAndClearInput();
    });

    // 渲染好友列表
    function renderFriends() {
        friendsUl.innerHTML = '';
        data.friends.forEach((friend, index) => {
            if (index < data.friend_states.length) {
                const li = document.createElement('li');
                li.textContent = `${friend} (${data.friend_states[index] == '1' ? '在线' : '离线'})`;
                li.classList.toggle('online', data.friend_states[index] == '1');
                li.addEventListener('click', () => openChat(friend, "0"));
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
            li.addEventListener('click', () => openChat(group, "1"));
            groupsUl.appendChild(li);
        });
    }

    // 打开聊天
    function openChat(name, groupFlag) {
        chatWith.textContent = `和 ${name} 聊天`;
        isGroup = groupFlag;
        renderChatHistory(myname + name);
    }

    // 渲染聊天历史记录
    function renderChatHistory(chatPartner) {
        chatMessages.innerHTML = '';
        const chatHistory = JSON.parse(localStorage.getItem(chatPartner)) || [];
        chatHistory.forEach(({ sender, message, type }) => {
            const messageDiv = createMessageElement(sender, message, type);
            chatMessages.appendChild(messageDiv);
        });
        chatMessages.scrollTop = chatMessages.scrollHeight;
    }

    // 处理收到的 WebSocket 消息
    function handleIncomingMessage(event) {
        try {
            const { sender, messages } = JSON.parse(event.data);
            const targetUser = sender;
            const message = messages;

            if (targetUser === chatWith.textContent.slice(2, -3)) {
                sendMessage('Ta', message, 'friend');
            }

            const historyName = myname + targetUser;
            saveMessageToLocalStorage(historyName, 'Ta', message, 'friend');
        } catch (error) {
            console.error('无法处理 WebSocket 消息:', error);
        }
    }

    // 发送消息并清空输入框
    function sendMessageAndClearInput() {
        const message = messageInput.value.trim();
        if (message) {
            const targetUser = chatWith.textContent.slice(2, -3);
            sendMessage('我', message, 'user');

            const historyName = myname + targetUser;
            saveMessageToLocalStorage(historyName, '我', message, 'user');
            messageInput.value = '';
            socket.send(JSON.stringify({ sender: myname, receiver: targetUser, isgroup: isGroup, message }));
        }
    }

    // 发送消息并将消息显示在聊天窗口
    function sendMessage(sender, message, messageType) {
        const messageDiv = createMessageElement(sender, message, messageType);
        chatMessages.appendChild(messageDiv);
        chatMessages.scrollTop = chatMessages.scrollHeight;
    }

    // 创建消息元素
    function createMessageElement(sender, message, messageType) {
        const messageDiv = document.createElement('div');
        messageDiv.classList.add('message', messageType);
        messageDiv.innerHTML = `${message}`;
        return messageDiv;
    }

    // 将消息保存到本地存储
    function saveMessageToLocalStorage(chatPartner, sender, message, messageType) {
        const chatHistory = JSON.parse(localStorage.getItem(chatPartner)) || [];
        chatHistory.push({ sender, message, type: messageType });
        localStorage.setItem(chatPartner, JSON.stringify(chatHistory));
    }
});
