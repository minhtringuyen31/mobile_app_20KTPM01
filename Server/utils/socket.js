import CategoryService from "../services/Category.service.js"
import UserServices from "../services/User.service.js";
const listCategory = await CategoryService.findAll();
const SocketListener = {
    start: function (io) {
        io.on('connection', async function (socket) {
            console.log('a user connected');
            // console.log(req.app.io);
            //implement with category ,product, confirm order
            socket.on("login", (data) => {
                global.userActive[data] = socket.id; // luu lau socket id cua user khi gui toi 
                console.log("User have id " + data + " online");
                console.log(global.userActive)
            })

            socket.on("newOrder", (data) => {
                const s = JSON.parse(data)
                console.log(s);
                const messages = {
                    "data": {
                        "username": "Thông báo đơn hàng",
                        "description": "Có 1 đơn hàng mới!Cần bạn xác nhận"
                    }
                }
                console.log(s.user_id);
                UserServices.handleTokenFireBase(41, messages, "one");
                console.log("Có 1 đơn hàng mới từ khách hàng có ID" + s);
            })
            socket.on("confirmOrder", (data) => {
        
                const messages = {
                    "data": {
                        "username": "Thông báo đơn hàng",
                        "description": "Đơn hàng của bạn đã được xác nhận"
                    }
                }
                UserServices.handleTokenFireBase(data, messages, "one");
                console.log("Có 1 đơn hàng mới từ khách hàng có ID" + data);
            })
            socket.on("deliverySuccess", (data) => {

                const messages = {
                    "data": {
                        "username": "Thông báo đơn hàng",
                        "description": "Đơn hàng của bạn đã được giao thành công"
                    }
                }
                UserServices.handleTokenFireBase(data, messages, "one");
                console.log("Có 1 đơn hàng mới từ khách hàng có ID" + data);
            })
            socket.on("cancelOrder", (data) => {

                const messages = {
                    "data": {
                        "username": "Thông báo đơn hàng",
                        "description": "Đơn hàng của bạn đã bị hủy"
                    }
                }
                UserServices.handleTokenFireBase(data, messages, "one");
                console.log("Có 1 đơn hàng mới từ khách hàng có ID" + data);
            })
            socket.on("refund", (data) => {

                const messages = {
                    "data": {
                        "username": "Thông báo đơn hàng",
                        "description": "Đơn hàng của bạn đã bị hủy"
                    }
                }
                UserServices.handleTokenFireBase(data, messages, "one");
                console.log("Có 1 đơn hàng mới từ khách hàng có ID" + data);
            })




            socket.on('disconnect', function () {
                const disconnectedUserId = Object.keys(global.userActive).find(
                    key => global.userActive[key] === socket.id
                );
                if (disconnectedUserId) {
                    delete global.userActive[disconnectedUserId];
                    console.log(`User ${disconnectedUserId} has disconnected.`);
                    console.log(global.userActive)
                }
            })
        });
    }
};
// config socket 
export default SocketListener;
