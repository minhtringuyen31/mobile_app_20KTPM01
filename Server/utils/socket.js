import CategoryService from '../services/Category.service.js';
import UserServices from '../services/User.service.js';
import NotificationService from '../services/Notification.service.js';
const listCategory = await CategoryService.findAll();
const SocketListener = {
  start: function (io) {
    io.on('connection', async function (socket) {
      // console.log(req.app.io);
      //implement with category ,product, confirm order
      socket.on('login', (data) => {
        global.userActive[data] = socket.id; // luu lau socket id cua user khi gui toi
        console.log('User have id ' + data + ' online');
        console.log(global.userActive);
      });

      socket.on('newOrder', (data) => {
        const s = JSON.parse(data);
        console.log(s);
        const messages = {
          data: {
            username: 'Thông báo đơn hàng',
            description: 'Có 1 đơn hàng mới!Cần bạn xác nhận',
          },
        };

        UserServices.handleTokenFireBase(41, messages, 'one');
        console.log('Có 1 đơn hàng mới từ khách hàng có ID' + s);
      });
      socket.on('confirmOrder', (data) => {
        const result = JSON.parse(data);

        const messages = {
          data: {
            username: 'Thông báo đơn hàng',
            description: 'Đơn hàng của bạn đã được xác nhận',
          },
        };
        //id: any, user_id: any, title: any, sub_title: any, image: any, description: any, time: any, type: any, is_seen: any
        const currentTime = new Date();

        NotificationService.postNotification(
          0,
          result.user_id,
          'Xác nhận đơn hàng',
          'Đơn hàng có mã  của bạn đã được xác nhận',
          '',
          result.shipping_address,
          currentTime,
          1,
          0
        );

        UserServices.handleTokenFireBase(result.user_id, messages, 'one');
        console.log('Có 1 đơn hàng mới từ khách hàng có ID' + data);
      });
      socket.on('deliverySuccess', (data) => {
        const result = JSON.parse(data);

        const currentTime = new Date();

        NotificationService.postNotification(
          0,
          result.user_id,
          'Giao hàng thành công',
          'Đơn hàng có mã  của bạn đã được giao thành công',
          '',
          result.shipping_address,
          currentTime,
          1,
          0
        );
        const messages = {
          data: {
            username: 'Thông báo đơn hàng',
            description: 'Đơn hàng của bạn đã được giao thành công',
          },
        };
        //
        console.log(result);
        UserServices.handleTokenFireBase(result.user_id, messages, 'one');
        console.log('Có 1 đơn hàng mới từ khách hàng có ID' + data);
      });
      socket.on('cancelOrder', (data) => {
        const result = JSON.parse(data);

        const currentTime = new Date();
        const messages = {
          data: {
            username: 'Thông báo đơn hàng',
            description: 'Đơn hàng của bạn đã bị hủy',
          },
        };

        NotificationService.postNotification(
          0,
          result.user_id,
          'Đơn hàng bị huỷ',
          'Đơn hàng  của bạn bị huỷ',
          '',
          result.shipping_address,
          currentTime,
          1,
          0
        );
        UserServices.handleTokenFireBase(result.user_id, messages, 'one');
        console.log('Có 1 đơn hàng mới từ khách hàng có ID' + data);
      });
      socket.on('refund', (data) => {
        const result = JSON.parse(data);
        const messages = {
          data: {
            username: 'Thông báo đơn hàng',
            description: 'Đơn hàng của bạn đã bị hủy',
          },
        };
        UserServices.handleTokenFireBase(result.user_id, messages, 'one');
        console.log('Có 1 đơn hàng mới từ khách hàng có ID' + data);
      });

      socket.on('disconnect', function () {
        const disconnectedUserId = Object.keys(global.userActive).find(
          (key) => global.userActive[key] === socket.id
        );
        if (disconnectedUserId) {
          delete global.userActive[disconnectedUserId];
          console.log(`User ${disconnectedUserId} has disconnected.`);
          console.log(global.userActive);
        }
      });
    });
  },
};
// config socket
export default SocketListener;
