console.log("Hello world");
const myFunction = (event) => {
    console.log("Came here");
    console.log(event)
    fetch("http://localhost:8080/cursor/stream", {
        method: "POST",
        body: JSON.stringify({
            xPosition: event.screenX,
            yPosition: event.screenY
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    });
}