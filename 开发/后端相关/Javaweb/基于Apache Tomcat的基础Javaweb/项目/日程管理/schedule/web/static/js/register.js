// 检验用户名格式是否合法的函数
function checkUsername(){
    // 定义正则表示字符串的规则:5到10位数字
    var usernameReg= /^[a-zA-Z0-9]{5,10}$/
    // 获得用户在页面上输入的信息
    var usernameInput =document.getElementById("usernameInput")
    var username = usernameInput.value
    // 获得格式提示的框
    var usernameMsg =document.getElementById("usernameMsg")
    // 格式有误时,返回false,在页面上提示
    if(!usernameReg.test(username)){ 
        usernameMsg.innerText="用户名格式有误"
        return false
    }
    usernameMsg.innerText="格式正确"

    // 格式校验后继续检测用户名是否被占用
    let request = new XMLHttpRequest()
    // 设置回调函数，设置响应回来的信息如何处理
    request.onreadystatechange=function(){
        if(request.readyState===4 && request.status===200){
            // 后端的响应的JSON字符串转换为前端的对象
            let response =JSON.parse(request.responseText)

            if(response.code !== 200){
                usernameMsg.innerText="用户名已存在"
            }else{
                usernameMsg.innerText="用户名可用"
            }
        }
    }
    // 设置请求方式和请求的资源路径
    request.open("GET","/user/checkUsernameUsed?username="+username);
    // 发送请求
    request.send();

    return true;
}

// 检验密码格式是否合法的函数
function checkUserPwd(){
    // 定义正则表示字符串的规则
    var  userPwdReg= /^[0-9]{6}$/
    // 获得用户在页面上输入的信息
    var userPwdInput =document.getElementById("userPwdInput")
    var userPwd = userPwdInput.value
    // 获得格式提示的框
    var userPwdMsg =document.getElementById("userPwdMsg")
    // 格式有误时,返回false,在页面上提示
    if(!userPwdReg.test(userPwd)){ 
        userPwdMsg.innerText="密码必须是6位数字"
        return false
    }
    // 格式OK,返回true 在页面上提示OK
    userPwdMsg.innerText="OK"
    return true
}

// 检验两次输入密码是否一致的函数
function checkConfirmPwd(){
    // 获得用户在页面上输入的信息
    var userPwdInput =document.getElementById("userPwdInput")
    var userPwd = userPwdInput.value
    var userConfirmPwdInput =document.getElementById("userConfirmPwdInput")
    var userConfirmPwd = userConfirmPwdInput.value
    // 获得格式提示的框
    var userPwdMsg = document.getElementById("userConfirmPwdMsg")
    // 格式有误时,返回false,在页面上提示
    if(userConfirmPwd!=userPwd){ 
        userPwdMsg.innerText="两次输入的密码不一致"
        return false
    }
    // 格式OK,返回true 在页面上提示OK
    userPwdMsg.innerText="OK"
    return true
}

// 表单在提交时,校验用户名和密码格式,格式OK才会提交
function checkForm(){
    var flag1 =checkUsername()
    var flag2 =checkUserPwd()
    var flag3 = checkConfirmPwd()

    return flag1&&flag2&&flag3
}

// 获取URL参数
const urlParams = new URLSearchParams(window.location.search);
const status_register = urlParams.get('status_register');
// 显示消息
const wrongPwdOrName = document.getElementById('wrongPwdOrName');
if (status_register === 'success') {
    wrongPwdOrName.innerText = '注册成功';
} else if (status_register === 'failed') {
    wrongPwdOrName.innerText = '注册失败,用户名可能已经被使用.';
}