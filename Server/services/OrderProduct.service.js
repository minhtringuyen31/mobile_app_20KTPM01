import OrderProductRepository from "../repositories/OrderProduct.repository.js"
const OrderProductServices = {
    // Đừng thắc mắc tại sao không gọi trực tiếp từ Respository --> Respository chỉ nơi CRUD database, còn services mình tự implement tuy thuộc vào yêu cầu !! 
    async create(note, order_id, product_id, quantity,price) {
        return await OrderProductRepository.create(note, order_id, product_id, quantity,price)
    },
    async update(id, note, order_id, product_id, quantity,price) {
        return await OrderProductRepository.update(id, note, order_id, product_id, quantity,price)
    },
    async delete(id) {
        return await OrderProductRepository.delete(id);
    },
    async findOne(id) {
        return await OrderProductRepository.findOneByID(id);
    },
    async findAll() {
        return await OrderProductRepository.findAll();
    }
    // Cần method gì thì tự implements !! 



}
export default OrderProductServices;