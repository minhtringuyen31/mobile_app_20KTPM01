import CategoryService from "../services/Category.service.js"
const listCategory = await CategoryService.findAll();
var userActive = {};
const SocketListener = {
    start: function (io) {
        io.on('connection', async function (socket) {
            console.log('a user connected');

            //implement with category ,product, confirm order
            socket.on("login", (data) => {
                userActive[data] = socket.id; // luu lau socket id cua user khi gui toi 
                console.log("User have id " + data + " online");
                console.log(userActive)
            })

            socket.on("123", (data) => {
                console.log("Client gửi lên: " + data);
            })

            socket.on("verify-confirm-order", (data) => {
                console.log(data);
                // waiting admin confirm order | implement code here or in controller
                io.to(userActive[data]).emit("verify-confirm-order", data) // gui toi nhung client nao can gui 
            })

            socket.on('disconnect', function () {
                const disconnectedUserId = Object.keys(userActive).find(
                    key => userActive[key] === socket.id
                );
                if (disconnectedUserId) {
                    delete userActive[disconnectedUserId];
                    console.log(`User ${disconnectedUserId} has disconnected.`);
                    console.log(userActive)
                }
            })
        });
    }
};
// config socket 
export default SocketListener;
