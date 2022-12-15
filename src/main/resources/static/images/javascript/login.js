
const form = document.querySelector("form");
const button = document.querySelector("button");
const IName = document.querySelector("#username");
const IPassword = document.querySelector("#password");

function showSuccess(){
}
function login(){
    fetch("http://localhost:8080/home-login.html",{
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        method: "POST",
        body: JSON.stringify({
            username: IName.value,
            password: IPassword.value
        })
    })
        .then(function (res){ console.log(res)})
        .catch(function (res){ console.log(res)})
}

