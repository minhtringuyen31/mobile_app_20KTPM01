import CartRepository from "../repositories/Cart.repository.js"
const CartServices = {
    // Đừng thắc mắc tại sao không gọi trực tiếp từ Respository --> Respository chỉ nơi CRUD database, còn services mình tự implement tuy thuộc vào yêu cầu !! 
    async create(user_id) {
        return await CartRepository.create(user_id)
    },
    async update(id, user_id) {
        return await CartRepository.update(id, user_id)
    },
    async delete(id) {
        return await CartRepository.delete(id);
    },
    async findOne(id) {
        return await CartRepository.findOneByID(id);
    },
    async findAll() {
        return await CartRepository.findAll();
    }
    // Cần method gì thì tự implements !! 
    


}
export default CartServices;