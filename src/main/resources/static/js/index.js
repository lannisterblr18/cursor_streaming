console.log("Hello world");
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/ws'
});
stompClient.activate();


const myFunction = (event) => {
    console.log("Came here");
    console.log(event)
    stompClient.publish({
        destination: "/app/stream-test",
        body: JSON.stringify({
            xPosition: event.offsetX,
            yPosition: event.offsetY
        })
    });
}