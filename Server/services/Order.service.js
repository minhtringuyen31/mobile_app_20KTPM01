import OrderRepository from "../repositories/Order.repository.js"
const OrderServices = {
    async changeStatus(id, status) {
        return await OrderRepository.changeStatus(id, status);
    },
    async create(user_id, order_date, shipping_address, total, status, promotion_id, payment_method_id) {
        return await OrderRepository.create(user_id, order_date, shipping_address, total, status, promotion_id, payment_method_id)
    },
    async update(id, user_id, order_date, shipping_address, total, status, promotion_id, payment_method_id) {
        return await OrderRepository.update(id, user_id, order_date, shipping_address, total, status, promotion_id, payment_method_id)
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
        return await OrderRepository.findProductOrder(orderId)
    }
    // Cần method gì thì tự implements !! 



}
export default OrderServices;