// 检验用户名格式是否合法的函数
function checkUsername(){
    // 定义正则表示字符串的规则:5到10位数字
    var  usernameReg= /^[a-zA-Z0-9]{5,10}$/
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
    // 格式OK,返回true 在页面上提示OK
    usernameMsg.innerText="OK"
    return true

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

// 表单在提交时,校验用户名和密码格式,格式OK才会提交
function checkForm(){
    var flag1 =checkUsername()
    var flag2 =checkUserPwd()

    return flag1&&flag2
}

// 获取URL参数
const urlParams = new URLSearchParams(window.location.search);
const status_login = urlParams.get('status_login');
// 显示消息
const wrongPwdOrName = document.getElementById('wrongPwdOrName');
if (status_login === 'wrongname') {
    wrongPwdOrName.innerText = '用户名不存在.';
} else if (status_login === 'wrongpswd') {
    wrongPwdOrName.innerText = '密码错误.';
}