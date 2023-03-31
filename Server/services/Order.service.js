import OrderRepository from "../repositories/Order.repository.js"
const OrderServices = {
    // Đừng thắc mắc tại sao không gọi trực tiếp từ Respository --> Respository chỉ nơi CRUD database, còn services mình tự implement tuy thuộc vào yêu cầu !! 
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
    }
    // Cần method gì thì tự implements !! 



}
export default OrderServices;