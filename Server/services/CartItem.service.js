import CartItemRepository from "../repositories/CartItem.repository.js"
const CartItemServices = {
    // Đừng thắc mắc tại sao không gọi trực tiếp từ Respository --> Respository chỉ nơi CRUD database, còn services mình tự implement tuy thuộc vào yêu cầu !! 
    async create(user_id, cart_id, product_id, quantity, size, price, topping, notes) {
        return await CartItemRepository.create(user_id, cart_id, product_id, quantity, size, price, topping, notes)
    },
    async update(id, user_id, cart_id, product_id, quantity, size, price, topping) {
        return await CartItemRepository.update(id, user_id, cart_id, product_id, quantity, size, price, topping)
    },
    async delete(id) {
        return await CartItemRepository.delete(id);
    },
    async findOne(id) {
        return await CartItemRepository.findOneByID(id);
    },
    async findAll() {
        return await CartItemRepository.findAll();
    }
    // Cần method gì thì tự implements !! 



}
export default CartItemServices;