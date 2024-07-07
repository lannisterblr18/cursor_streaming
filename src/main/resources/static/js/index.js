$(document).ready(function() {
    console.log("Hello world");
    const stompClient = new StompJs.Client({
        brokerURL: 'ws://localhost:8080/ws'
    });
    stompClient.activate();
    let count = 0;
    const limit = 10;
    $("#rectangle").mousemove(function (event) {
        console.log("Came here");
        console.log(event)
        if (count >= limit) {
            stompClient.publish({
                destination: "/app/stream-test",
                body: JSON.stringify({
                    xPosition: event.clientX,
                    yPosition: event.clientY
                })
            });
            count = 0;
        } else {
            count += 1;
        }
    })
});
