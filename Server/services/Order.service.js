import OrderRepository from '../repositories/Order.repository.js';
const OrderServices = {
  async totalOrder() {
    return await OrderRepository.totalOrder();
  },
  async countOrder() {
    return await OrderRepository.countOrder();
  },
  async changeStatus(id, status) {
    return await OrderRepository.changeStatus(id, status);
  },
  async create(
    user_id,
    order_date,
    shipping_address,
    total,
    status,
    promotion_id,
    payment_method_id
  ) {
    return await OrderRepository.create(
      user_id,
      order_date,
      shipping_address,
      total,
      status,
      promotion_id,
      payment_method_id
    );
  },
  async update(
    id,
    user_id,
    order_date,
    shipping_address,
    total,
    status,
    promotion_id,
    payment_method_id
  ) {
    return await OrderRepository.update(
      id,
      user_id,
      order_date,
      shipping_address,
      total,
      status,
      promotion_id,
      payment_method_id
    );
  },
  async delete(id) {
    return await OrderRepository.delete(id);
  },
  async findOne(id) {
    return await OrderRepository.findOneByID(id);
  },
  async findAll() {
    return await OrderRepository.findAll();
  },
  async findByUserId(userId) {
    return await OrderRepository.findByUserId(userId);
  },
  async findProductOrder(orderId) {
    return await OrderRepository.findProductOrder(orderId);
  },
  async creatRefund(orderId, token) {
    return await OrderRepository.createRefundOrder(orderId, token);
  },
  async findToken(orderId) {
    return await OrderRepository.findTokenByOrderId(orderId);
  },
  async findAllByDate() {
    return await OrderRepository.findAllByDate();
  },
  async findAllByWeek() {
    return await OrderRepository.findAllByWeek();
  },
  async findAllByMonth() {
    return await OrderRepository.findAllByMonth();
  },
  // Cần method gì thì tự implements !!
};
export default OrderServices;
