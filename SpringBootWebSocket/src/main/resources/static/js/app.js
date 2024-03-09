const url = 'ws://localhost:8080/spring-boot-websocket';

// 訂閱
const topic1 = "/topic/greetings";

// 調用服務
const app_register = "/app/register";

const client = new StompJs.Client({
	brokerURL: url
});

var buttonConnect;
var buttonDisconnect;

client.onConnect = (frame) => {
	console.log('Connected: ' + frame);
	// 監聽所訂閱的資料
	client.subscribe(topic1, (greeting) => {
		console.log('收到消息: ' + greeting.body);
	});
};

function connect() {
	client.activate();
	console.log('Connected');
}

function disconnect() {
	client.deactivate();
	console.log('Disconnected');
}

document.addEventListener("DOMContentLoaded", function() {
	buttonConnect = document.getElementById("connect");
	buttonDisconnect = document.getElementById("disconnect");
	
	buttonConnect.addEventListener("click", (e) => {
		connect();
		e.preventDefault(); // 停止該元件預設行為
	});
	
	buttonDisconnect.addEventListener("click", (e) => {
		disconnect();
		e.preventDefault(); // 停止該元件預設行為
	});
	
});
