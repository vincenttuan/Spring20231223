WebSocket STOMP
STOMP 協定
STOMP(Simple Text Orientated Messaging Protocol)是一種基於 WebSocket 的簡單訊息傳輸協定，
它定義了一些操作 WebSocket 的方法，包含 CONNECT、SEDN、SUBSCRIBE 等，

WebSocket STOMP（Simple Text Oriented Messaging Protocol）協定是一種為WebSocket設計的高級消息協議，允許客戶端和服務器之間進行雙向通信。STOMP提供了一種可互操作的連接格式，讓STOMP客戶端能夠與任何STOMP消息代理（broker）進行交互，從而促進了消息的發送和接收。

STOMP協定主要特點如下：

    簡單易懂：STOMP使用了一種類似於HTTP的框架，包括命令、頭部和正文。這種簡單的文本導向格式使得協定易於理解和實現。

    文本導向：STOMP消息是以文本形式發送的，這使得它易於調試和檢查。

    靈活的消息傳遞模式：STOMP支持包括點對點、發布/訂閱在內的多種消息交換模式，適用於廣泛的應用場景。

    互操作性：由於STOMP是高級的，它可以在任何語言或平台上實現，並且可以與任何遵循STOMP協議的消息代理一起使用，從而提高了不同系統之間的互操作性。

    支持身份驗證和事務：STOMP協定允許客戶端進行身份驗證並支持事務性消息處理。

STOMP協定的工作流程大致如下：

    連接：客戶端發送一個CONNECT命令到服務器，請求建立一個STOMP連接。服務器響應一個CONNECTED命令，表示連接已經建立。

    訂閱：客戶端發送SUBSCRIBE命令到服務器，訂閱一個或多個目的地的消息。

    發送消息：客戶端可以通過發送SEND命令到一個特定目的地來發送消息。

    接收消息：當消息到達訂閱的目的地時，服務器會將消息轉發給相應的客戶端。

    取消訂閱和斷開連接：客戶端可以發送UNSUBSCRIBE命令來取消訂閱，並通過發送DISCONNECT命令來斷開與服務器的連接。

STOMP協定因其簡單、靈活和高度互操作的特性，被廣泛應用於需要實時通信的Web應用中，如聊天應用、實時通知和協作工具等。