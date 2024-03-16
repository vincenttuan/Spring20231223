const url = 'ws://localhost:8080/spring-boot-websocket';

// 訂閱
const topic1 = "/topic/greetings";
const topic2 = "/topic/servertime";
const topic3 = "/topic/^TWII";
const queue1 = "/user/queue/commission-return";
const queue2 = "/user/queue/transaction-return";

// 調用服務
const app_register = "/app/register";
const app_order = "/app/order-cr"

const client = new StompJs.Client({
	brokerURL: url
});

var subscriptions = {}; // 存儲訂閱的物件

var buttonConnect;
var buttonDisconnect;
var buttonRegister;
var usermessageInput;
var servertime;

client.onConnect = (frame) => {
	setConnected(true);
	console.log('Connected: ' + frame);
	// 監聽所訂閱的資料
	client.subscribe(topic1, (greeting) => {
		console.log('收到消息: ' + greeting.body);
		var messageDisplay = document.getElementById('message-display');
		messageDisplay.innerHTML = greeting.body;
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
	client.subscribe(queue1, (commissionReturn) => {
		console.log('委託回報: ' + commissionReturn.body);
		var cr = JSON.parse(commissionReturn.body);
		var crBody = document.getElementById('commission-return-body');
		var crRow = document.createElement('tr');
		crRow.innerHTML = `<td>${cr.orderId}</td>
						   <td>${cr.status}</td>
						   <td>${cr.time}</td>
						   <td>${cr.order.bs}</td>
						   <td>${cr.order.symbol}</td>
						   <td>${cr.order.price}</td>
						   <td>${cr.order.amount}</td>`;
		crBody.appendChild(crRow);
		
	});
	client.subscribe(queue2, (transactionReturn) => {
		console.log('成交回報: ' + transactionReturn.body);
		var tx = JSON.parse(transactionReturn.body);
		var txBody = document.getElementById('transaction-return-body');
		var txRow = document.createElement('tr');
		txRow.innerHTML = `<td>${tx.txId}</td>
						   <td>${tx.status}</td>
						   <td>${tx.time}</td>
						   <td>${tx.cr.orderId}</td>
						   <td>${tx.cr.order.bs}</td>
						   <td>${tx.cr.order.symbol}</td>
						   <td>${tx.cr.order.price}</td>
						   <td>${tx.price}</td>
						   <td>${tx.cr.order.amount}</td>
						   <td>${tx.amount}</td>`;
		txBody.appendChild(txRow);
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
	console.log('message...');
	// 發送
	client.publish({
		destination: app_register,
		body: JSON.stringify({content: usermessageInput.value})
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
		quotesBody.appendChild(quoteRow);
	}
	quoteRow.innerHTML = `<td>${quote.symbol}</td>
					<td>${quote.成交}</td>
					<td>${quote.總量}</td>
					<td style='cursor: pointer' title='按我一下可以移除' onclick='cancelOrderSymbol("${quote.symbol}")'>X</td>
					<td style='cursor: pointer' title='按我一下可以買進' onclick='buyOrder("${quote.symbol}", ${quote.成交})'>買進</td>
					<td style='cursor: pointer' title='按我一下可以賣出' onclick='sellOrder("${quote.symbol}", ${quote.成交})'>賣出</td>`;

}

function cancelOrderSymbol(symbol) {
	console.log('取消股票訂閱: ' + symbol);
	var stockSymbol = symbol;
	var stockTopic = "/topic/" + stockSymbol;
	var quotesBody = document.getElementById("quotes-body");
	var quoteRow = document.getElementById("row-" + symbol);
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

// 下單傳送
function createAndPublishOrder(buyOrSell, stockSymbol, stockPrice, stockAmount = 1) {
	// 買賣方向, 股票代號, 價格, 數量 
	var order = {
		bs: buyOrSell,
		symbol: stockSymbol,
		price: stockPrice,
		amount: stockAmount
	};
	
	console.log('下單: ' + JSON.stringify(order));
	
	client.publish({
		destination: app_order,
		body: JSON.stringify(order)	
	});
}

// 買單
function buyOrder(symbol, price){
	console.log(`買進 ${symbol} 價格: ${price}`);
	createAndPublishOrder('B', symbol, price);
}

// 賣單
function sellOrder(symbol, price){
	console.log(`賣出 ${symbol} 價格: ${price}`);
	createAndPublishOrder('S', symbol, price);
}

document.addEventListener("DOMContentLoaded", function() {
	buttonConnect = document.getElementById("connect");
	buttonDisconnect = document.getElementById("disconnect");
	buttonRegister = document.getElementById("register");
	usermessageInput = document.getElementById('usermessage');
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
