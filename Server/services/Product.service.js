import ProductRepository from "../repositories/Product.repository.js"
const ProductServices = {
    // Đừng thắc mắc tại sao không gọi trực tiếp từ Respository --> Respository chỉ nơi CRUD database, còn services mình tự implement tuy thuộc vào yêu cầu !! 
    async create(name, description, size, price_S, price_M, price_L, image, status, category_id, update_date, release_date, sales) {
        return await ProductRepository.create(name, description, size, price_S, price_M, price_L, image, status, category_id, update_date, release_date, sales)
    },
    async update(id, name, description, size, price_S, price_M, price_L, image, status, category_id, update_date, release_date, sales) {
        return await ProductRepository.update(id, name, description, size, price_S, price_M, price_L, image, status, category_id, update_date, release_date, sales)
    },
    async delete(id) {
        return await ProductRepository.delete(id);
    },
    async findOne(id) {
        return await ProductRepository.findOneByID(id);
    },
    async findAll() {
        return await ProductRepository.findAll();
    }
    // Cần method gì thì tự implements !! 



}
export default ProductServices;