function $(id){
    return document.getElementById(id);
}

var xmlHttpRequest;

function createXMLHttpRequest() {
    if (window.XMLHttpRequest) {//符合DOM2标准的浏览器，xmlHttpRequest创建方式
        xmlHttpRequest = new XMLHttpRequest();
    } else if(window.ActiveXObject){//IE浏览器xmlHttpRequest创建方式
        try {
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (e) {
            xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP")
        }
    }
    // return xmlHttpRequest;
}

function ckUname(uname){
    //创建XMLHttpRequest对象
    createXMLHttpRequest();

    var url="user.do?operate=ckName&uname=" + uname;
    xmlHttpRequest.open("GET",url,true);
    //设置调用函数
    xmlHttpRequest.onreadystatechange = ckUnameCB;
    //发送请求
    xmlHttpRequest.send();

}
function ckUnameCB(){
    if(xmlHttpRequest.status==200 && xmlHttpRequest.readyState==4){
        let responseText = xmlHttpRequest.responseText;

        if (responseText=="{'uname':'1'}"){
            alert("用户名已注册！");
            // let unameTxt = $("unameTxt");
            // unameTxt.focus();

        }else {
            if (responseText=="{'uname':'0'}"){
            // alert("可以注册");
            let pwdTxt = $("pwdTxt");
            pwdTxt.focus();
            return;
            }
            alert(responseText);
        };

    }
}


function preRegist(){
    //检验用户名
    let unameTxt = $("unameTxt");
    let unameSpan = $("unameSpan");

    let uname = unameTxt.value;
    var unameReg =/^[a-zA-Z0-9]{6,16}/;

    if (!unameReg.test(uname)){
        unameSpan.style.visibility="visible";
        return false;
    }else{
        unameSpan.style.visibility="hidden";
    }
//检验密码
    let pwdTxt = $("pwdTxt");
    let pwd = pwdTxt.value;
    let pwdSpan = $("pwdSpan");
    let pwdReg = /^[a-zA-Z0-9_\-\@\#\&\*]{8,}$/;
    if (!pwdReg.test(pwd)) {
        pwdSpan.style.visibility="visible";
        return false;
    }else {
        pwdSpan.style.visibility="hidden";
    }
    let pwdCkTxt = $("pwdCkTxt");
    let pwdCk=pwdCkTxt.value;
    let pwdCkSpan = $("pwdCkSpan");
    if (pwd != pwdCk){
        pwdCkSpan.style.visibility="visible";
        return false;
    }else{
        pwdCkSpan.style.visibility="hidden";
    }

    //检验邮箱
    let emailTxt = $("emailTxt");
    let emailSpan = $("emailSpan");
    let emailReg=/^[a-zA-Z0-9_\.-]+@([a-zA-Z0-9-]+[\.]{1})+[a-zA-Z]+$/;
    if (!emailReg.test(emailTxt.value)){
        emailSpan.style.visibility="visible";
        return false
    }else{
        emailSpan.style.visibility="hidden";
    }

    return true;
}