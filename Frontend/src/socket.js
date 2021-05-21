import store from './store.js';
let ws;

connect();
function connect() {
  ws = new WebSocket('ws://localhost:4000/watch-collections');

  ws.onopen = () => {
    console.log('[WebSocket] connected');
  };

  ws.onclose = () => {
    console.log('[WebSocket] disconnected');
    setTimeout(() => {
      connect();
    }, 500);
  };

  ws.onmessage = (message) => {
    let data = JSON.parse(message.data);
    let newMessage = data.data[0];
    console.log(newMessage);
    store.commit('deleteBooking', newMessage);
  };
}
