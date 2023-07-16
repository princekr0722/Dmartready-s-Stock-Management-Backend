let baseUrl = "http://localhost:8080";

var stompClient = null;
var username = null;
let jwtToken;
let jwt;

let loginForm = document.querySelector("#login-form");
console.log(loginForm)

loginForm.addEventListener("submit", async (event)=>{
    event.preventDefault();

    let username = loginForm.querySelector("#username");
    let password = loginForm.querySelector("#password");

    const headers = new Headers();
    headers.append('Authorization', 'Basic ' + btoa(username + ':' + password));

    try {
        
        let res = await fetch(baseUrl + "/user-service-api/signIn", {
            method : "GET",
            headers : headers
        });

        if(!res.ok) {
            alert(await res.text());
            return;
        }

        jwt = res.headers.get("Authorization");
        console.log(jwt)
    } catch (error) {
        alert(error)
    }
})

// connect();
function connect(event) {
    try {
        if (event) event.preventDefault();
        // i can send the jwtToken here
        // jwtToken = usernameForm.querySelector("#jwt").value;

        var socket = new SockJS('http://localhost:8084/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({
            Authorization: "Bearer " + jwt,
        }, onConnected, onError);

    } catch (error) {
        alert(error)
    }
}

let tbody = document.querySelector("tbody");
async function onConnected() {

    let products = await getProducts(jwt);

    products.forEach(p => {
        let productId = p.id;
        let productName = p.name;
        let stock = p.totalStocks;

        let tr = 
        `<tr data-id="${productId}">
            <td>${productId}</td>
            <td>${productName}</td>
            <td>${stock}</td>
        </tr>`;

        tbody.insertAdjacentElement("beforeend", tr);

        stompClient.subscribe('/queue/' + productId, onMessageReceived, {
            Authorization: "Bearer " + jwtToken,
        });

        function onMessageReceived(payload) {
            var message = JSON.parse(payload.body);
        
            let trs = tbody.querySelectorAll("tr");
        
            for(let tr of trs) {
                if(tr.dataset.id == productId) {
                    tr.lastChild.innerHtml = message;
                }
            }
        }
    });

    sayHello(jwtToken);
    return;
}
function onError() {
    console.log("error")
}

async function getProducts(jwt) {

    try {
        
        let res = await fetch(baseUrl+"/product-service-api/products?pageSize=100&pageNumber=1", {
            method : "GET",
            headers : {
                Authorization: "Bearer "+jwt,
            }
        });

        if(!res.ok) {
            alert(await res.text());
            return;
        }

        let products = await res.json().pageContent;
        console.log(products);

        return products;
    } catch (error) {
        
    }

}