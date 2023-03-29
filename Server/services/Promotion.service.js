import PromotionRepository from "../repositories/Promotion.repository.js"
const PromotionServices = {
    // Đừng thắc mắc tại sao không gọi trực tiếp từ Respository --> Respository chỉ nơi CRUD database, còn services mình tự implement tuy thuộc vào yêu cầu !! 
    async create(name, description, discount, start_date, end_date) {
        return await PromotionRepository.create(name, description, discount, start_date, end_date)
    },
    async update(id, name, description, discount, start_date, end_date) {
        return await PromotionRepository.update(id, name, description, discount, start_date, end_date)
    },
    async delete(id) {
        return await PromotionRepository.delete(id);
    },
    async findOne(id) {
        return await PromotionRepository.findOneByID(id);
    },
    async findAll() {
        return await PromotionRepository.findAll();
    }
    // Cần method gì thì tự implements !! 



}

export default PromotionServices;