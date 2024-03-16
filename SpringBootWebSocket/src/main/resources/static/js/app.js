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

var subscriptions = {}; // 存儲訂閱的物件

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
		quote = JSON.parse(quote.body);
		document.getElementById('twii').innerHTML = "指數:" + quote.成交 + " 漲跌:" + quote.漲跌 + " 漲跌幅:" + quote.漲跌幅;
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

function orderSymbol() {
	var stockSymbol = symbolInput.value;
	var stockTopic = "/topic/" + stockSymbol;
	// 檢查是否已經訂閱
	if(subscriptions[stockSymbol]) {
		console.log('已經訂閱:' + stockTopic);
		return;
	}
	
	subscriptions[stockSymbol] = client.subscribe(stockTopic, (quoteResponse) => {
		console.log("收到訂閱的股票資料: " + quoteResponse.body);
		var quote = JSON.parse(quoteResponse.body);
		updateQuoteList(quote); // 更新列表資訊
	}); 
}

function updateQuoteList(quote) {
	var quotesBody = document.getElementById("quotes-body");
	var quoteRow = document.getElementById("row-" + quote.symbol);
	if(!quoteRow) { // 建立新 row
		quoteRow = document.createElement('tr');
		quoteRow.setAttribute('id', 'row-' + quote.symbol);
		quoteRow.innerHTML = `<td>${quote.symbol}</td><td>${quote.成交}</td><td>${quote.總量}</td><td onclick='cancelOrderSymbol("${quote.symbol}")'>X</td><td></td><td></td>`;
		quotesBody.appendChild(quoteRow);
	} else { // 更新 row
		quoteRow.innerHTML = `<td>${quote.symbol}</td><td>${quote.成交}</td><td>${quote.總量}</td><td onclick='cancelOrderSymbol("${quote.symbol}")'>X</td><td></td><td></td>`;
	}
}

function cancelOrderSymbol(symbol) {
	console.log('取消股票訂閱: ' + symbol);
	var stockSymbol = symbol;
	var stockTopic = "/topic/" + stockSymbol;
	var quotesBody = document.getElementById("quotes-body");
	var quoteRow = document.getElementById("row-" + quote.symbol);
	// 檢查是否存在於訂閱記錄檔中 ?
	if(!subscriptions[stockSymbol]) {
		console.log('無此訂閱: ' + stockSymbol);
		return;
	}
	
	// 取消訂閱
	subscriptions[stockSymbol].unsubscribe(); 
	
	// 刪除紀錄
	if(quoteRow) {
		quotesBody.removeChild(quoteRow);
	}
	
	// 從映射中移除訂閱參考
	delete subscriptions[stockSymbol];  
}

document.addEventListener("DOMContentLoaded", function() {
	buttonConnect = document.getElementById("connect");
	buttonDisconnect = document.getElementById("disconnect");
	buttonRegister = document.getElementById("register");
	usernameInput = document.getElementById('username');
	servertime = document.getElementById('servertime');
	showDiv = document.getElementById('show');
	
	symbolInput = document.getElementById('symbol');
	buttonOrderSymbol = document.getElementById('order_symbol');
	buttonCancelSymbol = document.getElementById('cancel_symbol');
	
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
	
	// 股票訂閱
	buttonOrderSymbol.addEventListener("click", (e) => {
		orderSymbol();
		e.preventDefault(); // 停止該元件預設行為
	});
	
	// 取消股票訂閱
	buttonCancelSymbol.addEventListener("click", (e) => {
		cancelSymbol();
		e.preventDefault(); // 停止該元件預設行為
	});
	
	
	setConnected(false);
});
