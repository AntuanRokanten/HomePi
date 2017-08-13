var stompClient = null;

function connect() {
    console.log("Start connecting via websocket");


    var socket = new SockJS('/pi-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

//        stompClient.send("/app/temp-and-hum-subscribe");
//
//        stompClient.subscribe('/topic/temp-and-hum', function (data) {
//            console.log("Temp: " + data)
//        });
//
//        stompClient.send("/app/motion-subscribe");
//
//        stompClient.subscribe('/topic/motion', function (data) {
//            console.log("Motion! " + data)
//        });


//        stompClient.send("/app/hello");
//        stompClient.subscribe('/topic/greetings', function (greeting) {
//            console.log("Wow! : "+ greeting)
//        });

        stompClient.send("/app/temp-and-hum");
        stompClient.send("/app/temp-and-hum-subscribe");
        stompClient.subscribe('/topic/temp-and-hum', function (greeting) {
            console.log("Wow! : "+ greeting)
        });


    });
}

function subscribeToMotionDetection() {
    stompClient.send("/motion-subscribe");

    stompClient.subscribe('/topic/motion', function (data) {
        console.log("Motion! " + data)
    });
}

function subscribeToTempAndHumUpdates() {
    stompClient.send("/temp-and-hum-subscribe");

    stompClient.subscribe('/topic/temp-and-hum', function (data) {
        console.log("Temp: " + data)
    });
}

$(function () {
    connect();
//    subscribeToTempAndHumUpdates()

    // $("#motion-subscribe").click(function () {
    //     subscribeToMotionDetection();
    // });
});

// function disconnect() {
//     if (stompClient !== null) {
//         stompClient.disconnect();
//     }
//     console.log("Disconnected");
// }