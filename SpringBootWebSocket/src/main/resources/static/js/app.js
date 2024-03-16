const url = 'ws://localhost:8080/spring-boot-websocket';

// 訂閱
const topic1 = "/topic/greetings";
const topic2 = "/topic/servertime";
const topic3 = "/topic/^TWII";

// 調用服務
const app_register = "/app/register";

const client = new StompJs.Client({
	brokerURL: url
});

var buttonConnect;
var buttonDisconnect;
var buttonRegister;
var usernameInput;
var servertime;

client.onConnect = (frame) => {
	setConnected(true);
	console.log('Connected: ' + frame);
	// 監聽所訂閱的資料
	client.subscribe(topic1, (greeting) => {
		console.log('收到消息: ' + greeting.body);
	});
	client.subscribe(topic2, (time) => {
		console.log('收到消息: ' + time.body);
		servertime.innerHTML = JSON.parse(time.body).content;
	});
	client.subscribe(topic3, (quote) => {
		console.log('收到報價: ' + quote.body);
		document.getElementById('twii').innerHTML = quote.成交 + " " + quote.漲跌 + " " + quote.漲跌幅;
	});
};

function connect() {
	client.activate();
	console.log('Connected');
}

function disconnect() {
	client.deactivate();
	console.log('Disconnected');
	setConnected(false);
}

function setConnected(connected) {
	buttonConnect.disabled = connected;
	buttonDisconnect.disabled = !connected;
	if(connected) {
		showDiv.style.display = "block";
	} else {
		showDiv.style.display = "none";
	}
}

function register() {
	console.log('註冊...');
	// 發送
	client.publish({
		destination: app_register,
		body: JSON.stringify({content: usernameInput.value})
	});
}

document.addEventListener("DOMContentLoaded", function() {
	buttonConnect = document.getElementById("connect");
	buttonDisconnect = document.getElementById("disconnect");
	buttonRegister = document.getElementById("register");
	usernameInput = document.getElementById('username');
	servertime = document.getElementById('servertime');
	showDiv = document.getElementById('show');
	
	buttonConnect.addEventListener("click", (e) => {
		connect();
		e.preventDefault(); // 停止該元件預設行為
	});
	
	buttonDisconnect.addEventListener("click", (e) => {
		disconnect();
		e.preventDefault(); // 停止該元件預設行為
	});
	
	buttonRegister.addEventListener("click", (e) => {
		register();
		e.preventDefault(); // 停止該元件預設行為
	});
	
	setConnected(false);
});
